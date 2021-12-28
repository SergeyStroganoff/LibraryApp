const AppClass = class {
    // тело класса
    constructor() {
    }

    btnLogoutElement = document.querySelector('#btn-logout');
    roleElement = document.querySelector(".hidden-role-value");
    adminMenuElements = document.querySelectorAll(".admin-menu");

    changeDisplayStyleElements(elements, displayStyle) {
        for (let elem of elements) {
            elem.style.display = displayStyle;
        }
    }

    init() {
        this.btnLogoutElement.addEventListener('click', () => {
            document.location.href = "../controller?command=LOGOUT";
        });
        if (this.roleElement.getAttribute('value') === 'user') {
            this.changeDisplayStyleElements(this.adminMenuElements, "none");
        }
    }
}

myApp = new AppClass;
myApp.init();

