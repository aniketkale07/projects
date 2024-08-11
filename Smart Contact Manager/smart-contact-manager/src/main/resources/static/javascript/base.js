console.log("This is the Common javascript")
document.addEventListener('DOMContentLoaded', function () {
    const toggleButton = document.querySelector('[data-drawer-toggle="separator-sidebar"]');
    const sidebar = document.getElementById('separator-sidebar');
    const content = document.getElementById('main-content');

    toggleButton.addEventListener('click', function () {
        sidebar.classList.toggle('-translate-x-full');
        content.classList.toggle('ml-64'); // Shift content when sidebar opens
    });
});
