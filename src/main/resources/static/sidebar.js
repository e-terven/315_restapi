// Получаем все кнопки вкладок и контент вкладок
const tabButtons = document.querySelectorAll('.tab-button');
const tabContents = document.querySelectorAll('.tab-content');

// Первоначально скрываем все контенты вкладок кроме первой
tabContents.forEach(tabContent => {
    if (tabContent !== tabContents[0]) {
        tabContent.style.display = 'none';
    }
});

// Определяем текущую активную вкладку
let activeTab = 0;

// Добавляем обработчик на клик для каждой кнопки вкладки
tabButtons.forEach((tabButton, index) => {
    tabButton.addEventListener('click', () => {
        // Если кликнули на уже активную вкладку, ничего не делаем
        if (index === activeTab) {
            return;
        }

        // Если есть активная вкладка, скрываем ее
        if (activeTab !== null) {
            tabContents[activeTab].style.display = 'none';
        }

        // Открываем следующую вкладку только после того, как предыдущая закрыта
        setTimeout(() => {
            tabContents[index].style.display = 'block';
            activeTab = index;
        }, 500); // задержка в 0.5 секунды
    });
});