/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var editorcfg = {}
editorcfg.tabSpaces = "&nbsp;&nbsp;&nbsp;";
var editor = new RichTextEditor("#content", editorcfg);

$("#form-post").submit(function (event) {
    for (var i = 0; i < listDash.length; i++) {
        $("<input />").attr("type", "hidden")
                .attr("name", "listDash")
                .attr("value", listDash[i])
                .appendTo("#form-post");
    }
    for (var i = 0; i < listImages.length; i++) {
        $("<input />").attr("type", "hidden")
                .attr("name", "listImage")
                .attr("value", listImages[i])
                .appendTo("#form-post");
    }
    return true;
});

var currenAvatar = $("#image-preview").attr("src");
var file;
$("#thumbnail").on('change', function (e) {
    if (file) {
        URL.revokeObjectURL(file.preview);
    }
    file = e.target.files[0];
    file.preview = URL.createObjectURL(file);
    $("#image-preview").attr("src", file.preview);
     $("#icon-upload").addClass("hidden");
});
window.onbeforeunload = function (e) {
    URL.revokeObjectURL(file.preview);
};

