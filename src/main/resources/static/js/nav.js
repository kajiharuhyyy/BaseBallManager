/**
 * 
 */
const trigger = document.querySelector('.menu-trigger');
const menu = document.querySelector('.dropdown-content');

if (trigger && menu) {
  trigger.addEventListener('click', (e) => {
    menu.classList.toggle('show');
    e.stopPropagation();
  });

  window.addEventListener('click', () => {
    menu.classList.remove('show');
  });
}
