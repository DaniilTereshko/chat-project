$(document).ready(function() {
    // Функция для отправки PUT запроса
    function sendPutRequest(userId, role) {
        $.ajax({
            url: '/chat-project-1.0.0/api/admin/users?id=' + userId+'&role='+role,
            type: 'PUT',
            success: function(response) {
                alert('Роль пользователя успешно изменена');
                location.reload(); // Перезагрузить страницу после изменения
            },
            error: function(xhr, status, error) {
                if (xhr.status === 400) {
                    alert('Ошибка при изменении пользователя: ' + xhr.responseText);
                } else {
                    alert('Ошибка при изменении пользователя: ' + error);
                }
                location.reload();
            }
        });
    }

    // Функция для отправки DELETE запроса
    function sendDeleteRequest(userId) {
        var confirmation = confirm('Вы уверены, что хотите удалить пользователя с ID ' + userId + '?');

        if (confirmation) {
            $.ajax({
                url: '/chat-project-1.0.0/api/admin/users?id=' + userId,
                type: 'DELETE',
                success: function(response) {
                    alert('Пользователь успешно удален');
                    location.reload(); // Перезагрузить страницу после удаления
                },
                error: function(xhr, status, error) {
                    alert('Ошибка при удалении пользователя: ' + error);
                }
            });
        }
    }

    // Обработчик события для кнопки "Изменить"
    $('.edit-button').click(function(e) {
        e.preventDefault(); // Предотвращаем обычное поведение кнопки submit
        var userId = $(this).attr('data-user-id');
        var role = $(this).closest('tr').find('input[type="radio"]:checked').val();
        sendPutRequest(userId, role);
    });

    // Обработчик события для кнопки "Удалить"
    $('.delete-button').click(function(e) {
        e.preventDefault(); // Предотвращаем обычное поведение кнопки submit
        var userId = $(this).attr('data-user-id');
        sendDeleteRequest(userId);
    });
});
