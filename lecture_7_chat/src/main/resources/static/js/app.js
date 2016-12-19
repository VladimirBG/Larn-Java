(function () {

    var stompClient = null;

    function connect() {
        var socket = new SockJS('/ws-swords');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/chat/general', function (data) {
                var message = JSON.parse(data.body);
                showChatMessage(message.name, message.text, message.system);
            });
        });
    }

    function disconnect() {
        if (stompClient != null) {
            stompClient.disconnect();
        }
        console.log('Disconnected');
    }

    function sendChatMessage() {
        stompClient.send('/app/chat', {}, JSON.stringify({
            'name': $("#nameInput").val(),
            'text': $("#textInput").val()
        }));
    }

    function showChatMessage(name, text, system) {
        var $div = $('<div>').addClass('message')
            .append($('<span>').addClass('name').text(name))
            .append($('<span>').addClass('text').text(text));

        if (system) $div.addClass('system');
        $('#output').append($div);
    }

    $(function () {
        connect();

        $(window).bind("beforeunload", function () {
            disconnect();
        });

        $("form#chatForm").on('submit', function (e) {
            e.preventDefault();
            sendChatMessage();
        });
    });

}());