package org.stroganov.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.stroganov.entities.Author;
import org.stroganov.entities.Book;
import org.stroganov.entities.BookMark;
import org.stroganov.entities.User;

import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

public class MySQLLibraryDAO implements LibraryDAO {

    public static final String HIBERNATE_ERROR_MESSAGE = "Hibernate error when ";
    public static final String AUTHOR_NAME = "authorName";
    private final SessionFactory sessionFactory;
    private static MySQLLibraryDAO instance;
    private final Logger logger = Logger.getLogger(MySQLLibraryDAO.class);

    public static synchronized MySQLLibraryDAO getInstance(SessionFactory sessionFactory) {
        if (instance == null) {
            instance = new MySQLLibraryDAO(sessionFactory);
        }
        return instance;
    }

    public MySQLLibraryDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean addBook(Book book) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            if (session.get(Book.class, book.getNumberISBN()) == null) {
                Query<Author> query = session.createQuery("FROM Author WHERE authorName=:authorName", Author.class)
                        .setParameter(AUTHOR_NAME, book.getAuthor().getAuthorName());
                Author author = query.uniqueResult();
                if (author == null) {
                    session.save(book.getAuthor());
                } else {
                    book.setAuthor(author);
                }
                session.saveOrUpdate(book);
                transaction.commit();
                return true;
            } else {
                return false;
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(HIBERNATE_ERROR_MESSAGE + "addBook with number:" + book.getNumberISBN(), e);
            return false;
        }
    }

    @Override
    public boolean addBook(List<Book> bookList) {
        boolean isAllBooksWasAdded = true;
        for (Book book : bookList) {
            if (!addBook(book)) {
                isAllBooksWasAdded = false;
            }
        }
        return isAllBooksWasAdded;
    }

    @Override
    public boolean deleteBook(Book book) {
        findAllBookMarksInBook(book).forEach(this::deleteBookMark);
        return deleteEntity(book, "deleteBook");
    }


    @Override
    public Book findBook(String numberISBN) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Book.class, numberISBN);
        } catch (HibernateException e) {
            logger.error(HIBERNATE_ERROR_MESSAGE + "findBook with numberISBN: " + numberISBN, e);
            return null;
        }
    }


    @Override
    public List<Book> findBooksByPartName(String partOfName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Book> query = session.createQuery("FROM Book WHERE bookName like :partOfName", Book.class)
                    .setParameter("partOfName", "%" + partOfName + "%");
            return query.getResultList();
        } catch (HibernateException e) {
            logger.error(HIBERNATE_ERROR_MESSAGE + "findBooksByPartName: " + partOfName, e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Book> findBooksByPartAuthorName(String partAuthorName) {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<Book> query = session.createQuery("FROM Book b  WHERE b.authorName.authorName like :partOfAuthorName", Book.class)
                    .setParameter("partOfAuthorName", "%" + partAuthorName + "%");
            return query.getResultList();
        } catch (HibernateException e) {
            logger.error(HIBERNATE_ERROR_MESSAGE + "findBooksByPartAuthorName: " + partAuthorName, e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Book> findBooksByYearsRange(int firstYear, int secondYear) {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<Book> query = session.createQuery("FROM Book b  WHERE b.yearPublishing >= :firstYear and  b.yearPublishing <= :secondYear", Book.class)
                    .setParameter("firstYear", firstYear)
                    .setParameter("secondYear", secondYear);
            return query.getResultList();
        } catch (HibernateException e) {
            logger.error(HIBERNATE_ERROR_MESSAGE + "findBooksByYearsRange", e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Book> findBooksByParameters(int bookYear, int bookPages, String partBookName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Book> query = session.createQuery("FROM Book b WHERE b.yearPublishing = :bookYear and " +
                            "b.pagesNumber = :bookPages and b.bookName  like :partOfName", Book.class)
                    .setParameter("bookYear", bookYear)
                    .setParameter("bookPages", bookPages)
                    .setParameter("partOfName", "%" + partBookName + "%");
            return query.getResultList();
        } catch (HibernateException e) {
            logger.error(HIBERNATE_ERROR_MESSAGE + "findBooksByParameters ", e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Book> findBooksWithUserBookMarks(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<Book> query = session.createQuery(" SELECT b FROM BookMark bm" +
                            " LEFT JOIN Book b on bm.book=b" +
                            " LEFT JOIN User u on bm.user=u" +
                            " WHERE u.login = :login", Book.class)
                    .setParameter("login", user.getLogin());
            return query.getResultList();

        } catch (HibernateException e) {
            logger.error(HIBERNATE_ERROR_MESSAGE + "findBooksWithUserBookMarks: " + user.getLogin(), e);
        }
        return Collections.emptyList();
    }

    @Override
    public boolean addUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            if (session.get(User.class, user.getLogin()) == null) {
                session.save(user);
                transaction.commit();
                return true;
            } else {
                return false;
            }
        } catch (HibernateException e) {
            logger.error(HIBERNATE_ERROR_MESSAGE + "addUser with login:" + user.getLogin(), e);
            return false;
        }
    }

    @Override
    public User findUser(String userLogin) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, userLogin);
        } catch (HibernateException e) {
            logger.error(HIBERNATE_ERROR_MESSAGE + "findUser: " + userLogin, e);
            return null;
        }
    }

    @Override
    public boolean deleteUser(User user) {
        findUserBookMarks(user).forEach(this::deleteBookMark);
        return deleteEntity(user, "deleteUser");
    }

    @Override
    public boolean blockUser(User user) {
        return changeUserStatus(user, true);
    }

    @Override
    public boolean unblockUser(User user) {
        return changeUserStatus(user, false);
    }

    public boolean changeUserStatus(User user, boolean blockStatus) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            user.setBlocked(blockStatus);
            session.update(user);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            logger.error(HIBERNATE_ERROR_MESSAGE + "changeUserStatus user: " + user.getLogin());
        }
        return false;
    }

    @Override
    public boolean addBookMark(BookMark bookMark) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(bookMark);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            logger.error(HIBERNATE_ERROR_MESSAGE + "addBookMark" + bookMark, e);
            return false;
        }
    }

    @Override
    public boolean deleteBookMark(BookMark bookMark) {
        return deleteEntity(bookMark, "deleteBookMark");
    }

    @Override
    public boolean addAuthor(Author author) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Author> query = session.createQuery("FROM Author WHERE authorName=:authorName", Author.class)
                    .setParameter(AUTHOR_NAME, author.getAuthorName());
            Author authorFromDB = query.uniqueResult();
            if (authorFromDB == null) {
                session.save(author);
                transaction.commit();
                return true;
            }
        } catch (HibernateException e) {
            logger.error(HIBERNATE_ERROR_MESSAGE + "addAuthor with name:" + author.getAuthorName(), e);
        }
        return false;
    }

    @Override
    public Author findAuthor(String authorName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Author> query = session.createQuery("FROM Author WHERE authorName = :authorName", Author.class)
                    .setParameter(AUTHOR_NAME, authorName);
            return query.uniqueResult();
        } catch (HibernateException e) {
            logger.error(HIBERNATE_ERROR_MESSAGE + "findAuthor:  " + authorName, e);
        }
        return null;
    }

    @Override
    public boolean deleteAuthorWithAllHisBooks(Author author) {
        findBooksByPartAuthorName(author.getAuthorName()).forEach(this::deleteBook);
        return deleteEntity(author, "deleteAuthorWithAllHisBooks");
    }

    private <T> boolean deleteEntity(T t, String actionType) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(t);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            logger.error(HIBERNATE_ERROR_MESSAGE + actionType);
        }
        return false;
    }

    private List<BookMark> findUserBookMarks(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<BookMark> query = session.createQuery(" SELECT bm FROM BookMark bm" +
                            " LEFT JOIN Book b on bm.book=b" +
                            " LEFT JOIN User u on bm.user=u" +
                            " WHERE u.login = :login", BookMark.class)
                    .setParameter("login", user.getLogin());
            return query.getResultList();
        } catch (HibernateException e) {
            logger.error(HIBERNATE_ERROR_MESSAGE + "findUserBookMarks: " + user.getLogin(), e);
        }
        return Collections.emptyList();
    }

    private List<BookMark> findAllBookMarksInBook(Book book) {
        try (Session session = sessionFactory.openSession()) {
            Query<BookMark> query = session.createQuery(" SELECT bm FROM BookMark bm" +
                            " LEFT JOIN Book b on bm.book=b" +
                            " LEFT JOIN User u on bm.user=u" +
                            " WHERE b.numberISBN = :numberISBN", BookMark.class)
                    .setParameter("numberISBN", book.getNumberISBN());
            return query.getResultList();
        } catch (HibernateException e) {
            logger.error(HIBERNATE_ERROR_MESSAGE + "findAllBookMarksInBook: ", e);
        }
        return Collections.emptyList();

    }
}
