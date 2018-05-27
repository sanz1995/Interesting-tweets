
console.log("hola");

function mostrar(){
    $.get("words",{})
        .done( function(data) {
            var text = "";

            data.forEach(function(d){
                text+=d.word + ',';
            })

            $("textarea#words").val(text.substring(0, text.length - 1));


        }).fail(function() {
        //$("#resultsSearch").empty();
    });
}


function modificarKeyword(){

    var array = JSON.stringify($("textarea#words").val().split(','));

    var obj = '{'
        +'"words" : ' + array
        +'}';


    $.ajax({
        type        :   'PUT'  ,
        url         :   'words',
        data        :   obj,
        contentType :   "application/json; charset=utf-8",
        success     :   function(){
            mostrar();

            $("#success-alert").show();

        }
    });

}


function modificarLanguage(){

    var obj = '{'
        +'"lang" : ' + $("#sel1").val()
        +'}';
    $.ajax({
        type        :   'PUT'  ,
        url         :   'language',
        data        :   obj,
        contentType :   "application/json; charset=utf-8",
        success     :   function(){
            $("#success-alert").show();

        }
    });
}

function modificarLanguageDefault(){

    var obj = '{'
        +'"lang" : ' + $("#sel2").val()
        +'}';
    $.ajax({
        type        :   'PUT'  ,
        url         :   'languageDefault',
        data        :   obj,
        contentType :   "application/json; charset=utf-8",
        success     :   function(){
            $("#success-alert").show();

        }
    });
}


function mostrar(){
    $.get("words",{})
        .done( function(data) {
            var text = "";

            data.forEach(function(d){
                text+=d.word + ',';
            })

            $("textarea#words").val(text.substring(0, text.length - 1));


        }).fail(function() {
        //$("#resultsSearch").empty();
    });
}





function showChooser(){
    $("#chooser").show();
    $("#trans").hide();
    $("#distinguer").hide();
    $("#success-alert").hide();

}

function showTranslator(){
    $("#chooser").hide();
    $("#trans").show();
    $("#distinguer").hide();
    $("#success-alert").hide();
}

function showLanguages(){
    $("#chooser").hide();
    $("#trans").hide();
    $("#distinguer").show();
    $("#success-alert").hide();
}


$(document).ready (function() {
    $("#success-alert").hide();
    showChooser();
});

mostrar();
