checkBoxElement = document.querySelector('.form-check-input');
checkBoxElement.addEventListener('click', () => {
    if (checkBoxElement.checked) {
        checkBoxElement.setAttribute('value', 'true');
    }
});

