// JavaScript source code
var type;
function change()
{
    console.log("In change()");
    var id = event.srcElement.id;
    var string = document.getElementById(id).innerHTML;
    document.getElementById("dropbutton").innerHTML = string;
    type = string;
}

function resize(id) {
    var element = document.getElementById(id);
    element.style.height = 'auto';
    element.style.height = element.scrollHeight + 'px';
}

function init() {
    var textarea = document.getElementsByTagName('textarea');
    for (var i = 0, inb = textarea.length; i < inb; i++) {
        if (textarea[i].getAttribute('data-resizable') == 'true')
            resizeTextarea(textarea[i].id);
    }
}

addEventListener('DOMContentLoaded', init);

function submit() {
    

}