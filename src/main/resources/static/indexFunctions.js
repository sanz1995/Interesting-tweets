
var stompClient = null;
var subs = null;

function connect(name) {
    var socket = new SockJS('/twitter');

    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        var query = $("#q").val();
        subs = stompClient.subscribe(name, function(m) {
            $("#resultsBlock").prepend(Mustache.render(template, JSON.parse(m.body)));
        });

    });
}

function disconnect(){
    if (subs!=null){
        subs.unsubscribe();
        $("#resultsBlock").empty();
    }
}




function registerTemplate() {
    template = $("#template").html();
    Mustache.parse(template);
}

function registerStreaming() {
    $("#traduccion").click(function(event){
        disconnect();
        event.preventDefault();
        connect("/queue/translation/");

    });


    $("#original").click(function(event){
        console.log("original");
        disconnect();
        event.preventDefault();
        connect("/queue/original/");

    });
}



function registerSearch() {
    $("#search").submit(function(event){
        event.preventDefault();
        var target = $(this).attr('action');
        var query = $("#q").val();
        $.get("tweets",{})
            .done( function(data) {
                $("#resultsSearch").empty();
                console.log("vaciado");

                data.forEach(function(element) {
                    $("#resultsSearch").prepend(Mustache.render(template, element));
                });

            }).fail(function() {
                //$("#resultsSearch").empty();
        });
    });
}


$(document).ready(function() {
    console.log("hola");
    registerTemplate()
	registerSearch();
    registerStreaming();
});


