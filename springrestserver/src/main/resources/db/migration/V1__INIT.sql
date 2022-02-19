create TABLE authors
(
    authorid   INT AUTO_INCREMENT NOT NULL,
    authorname VARCHAR(255)       NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (authorid)
);

create TABLE bookmarks
(
    id             INT AUTO_INCREMENT NOT NULL,
    bookpagenumber INT                NULL,
    login          VARCHAR(255)       NULL,
    book_id        VARCHAR(255)       NULL,
    user_login     VARCHAR(255)       NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

create TABLE books
(
    id                  VARCHAR(255) NOT NULL,
    bookname            VARCHAR(255) NULL,
    pagesnumber         INT          NULL,
    yearpublishing      INT          NULL,
    authorName_authorid INT          NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

create TABLE `history`
(
    id            INT AUTO_INCREMENT NOT NULL,
    event         VARCHAR(255)       NULL,
    localDateTime datetime           NULL,
    user_login    VARCHAR(255)       NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);

create TABLE users
(
    login     VARCHAR(255) NOT NULL,
    fullname  VARCHAR(255) NOT NULL,
    isadmin   BIT(1)       NULL,
    isblocked BIT(1)       NULL,
    passcode  VARCHAR(255) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (login)
);

alter table `history`
    ADD CONSTRAINT FK2cfgbfxr5s3hrd9jaktnjy7ik FOREIGN KEY (user_login) REFERENCES users (login) ON update RESTRICT ON delete RESTRICT;

create index FK2cfgbfxr5s3hrd9jaktnjy7ik on `history` (user_login);

alter table books
    add CONSTRAINT FK8wbiar62k1fe3mikw9kepa2ir FOREIGN KEY (authorName_authorid) REFERENCES authors (authorid) ON update RESTRICT ON delete RESTRICT;

create index FK8wbiar62k1fe3mikw9kepa2ir on books (authorName_authorid);

alter table bookmarks
    add CONSTRAINT FKeh76my11yn2vdd8b6rm53rnqr FOREIGN KEY (user_login) REFERENCES users (login) ON update RESTRICT ON delete RESTRICT;

create index FKeh76my11yn2vdd8b6rm53rnqr on bookmarks (user_login);

alter table bookmarks
    add CONSTRAINT FKevg2kut22i380wdyfpgmsl6kq FOREIGN KEY (book_id) REFERENCES books (id) ON update RESTRICT ON delete RESTRICT;

create index FKevg2kut22i380wdyfpgmsl6kq on bookmarks (book_id);

alter table bookmarks
    add CONSTRAINT FKj9gmb6wyc7n2sfomlboway862 FOREIGN KEY (login) REFERENCES users (login) ON update RESTRICT ON delete RESTRICT;

create index FKj9gmb6wyc7n2sfomlboway862 on bookmarks (login);