function API() {
    //var ui = new UI();
}
API.prototype.getList = function () {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "data/getAll",
        data: "",
        dataType: 'json',
        timeout: 100000,
        success: function (data) {
            console.log("SUCCESS: ", data);
            UI.displayList(data);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        },
        done: function (e) {
            console.log("DONE");
        }
    });
}

API.prototype.addData = function () {
    var requestData = {}
    requestData["stringValue"] = $("#stringValue").val();
    requestData["position"] = null;
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "data/add",
        data: JSON.stringify(requestData),
        dataType: 'json',
        timeout: 100000,
        success: function (data) {
            console.log("SUCCESS: ", data);
            UI.displayList(data);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        },
        done: function (e) {
            console.log("DONE");
        }
    });
}

API.prototype.deleteData = function () {
    var requestData = {}
    requestData["stringValue"] = null;
    requestData["position"] = $("#dataList .active").index();
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "data/delete",
        data: JSON.stringify(requestData),
        dataType: 'json',
        timeout: 100000,
        success: function (data) {
            console.log("SUCCESS: ", data);
            UI.displayList(data);
            if ($("#dataList li").size() > 0) {
                UI.listItemClick();
                $("#dataList li").eq((requestData["position"] >= $("#dataList li").size()) ? $("#dataList li").size() - 1 : requestData["position"]).addClass('active');
            }
            if ($("#dataList li").size() == 0) {
               UI.dissableControls();
            }
        },
        error: function (e) {
            console.log("ERROR: ", e);
        },
        done: function (e) {
            console.log("DONE");
        }
    });
}

API.prototype.updateData = function () {
    var requestData = {}
    requestData["stringValue"] = $("#stringValue").val();
    requestData["position"] = $("#dataList .active").index();
    ;

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "data/update",
        data: JSON.stringify(requestData),
        dataType: 'json',
        timeout: 100000,
        success: function (data) {
            console.log("SUCCESS: ", data);
            UI.displayList(data);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        },
        done: function (e) {
            console.log("DONE");
        }
    });
}

API.prototype.moveUpData = function () {
    var requestData = {}
    requestData["stringValue"] = null;
    requestData["position"] = $("#dataList .active").index();
    ;

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "data/moveUp",
        data: JSON.stringify(requestData),
        dataType: 'json',
        timeout: 100000,
        success: function (data) {
            console.log("SUCCESS: ", data);
            UI.displayList(data);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        },
        done: function (e) {
            console.log("DONE");
        }
    });
}

API.prototype.moveDownData = function () {
    var requestData = {}
    requestData["stringValue"] = null;
    requestData["position"] = $("#dataList .active").index();
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "data/moveDown",
        data: JSON.stringify(requestData),
        dataType: 'json',
        timeout: 100000,
        success: function (data) {
            console.log("SUCCESS: ", data);
            UI.displayList(data);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        },
        done: function (e) {
            console.log("DONE");
        }
    });
}

function UI() {
    this.api = new API();
}

UI.displayList = function (data) {
    $("#dataList").empty();
    $.each(data['result'], function (index, item) {
        $("#dataList").append(
            $(document.createElement('li')).addClass("list-group-item").text(item.stringValue)
        );
    });
}

UI.display = function (data) {
    var json = "<h4>Ajax Response</h4><pre>"
        + JSON.stringify(data, null, 4) + "</pre>";
    $('#feedback').html(json);
}

UI.listItemClick = function () {
    $('#dataList li').removeClass('active');
    //$("#stringValue").val($(this).text());
    var e = window.event || e;
    e.stopPropagation();
    this.enableControls();

}

UI.enableControls = function () {
    $('#dataControls').removeClass('hidden');
    $('#btn-update').removeClass('hidden');
}

UI.dissableControls = function () {
    $('#dataControls').addClass('hidden');
    $('#btn-update').addClass('hidden');
    $("#stringValue").val('');
}

UI.prototype.submitFormAction = function () {
    var $btn = $(document.activeElement);
    event.preventDefault();
    if ($btn.attr('id') == 'btn-update') {
        this.api.updateData();
    }
    else {
        this.api.addData();
    }
    UI.dissableControls()
}

UI.prototype.getList = function () {
    this.api.getList();
}

UI.prototype.add = function () {
    this.api.addData();
}
UI.prototype.delete = function () {
    this.api.deleteData();
}

//UI.prototype.update = function () {
//    this.api.updateData();
//}
UI.prototype.moveUp = function () {
    this.api.moveUpData();
}
UI.prototype.moveDown = function () {
    this.api.moveDownData();
}


jQuery(document).ready(function ($) {

    //var api = new API();
    var ui = new UI();
    ui.getList();

    $("#dataList").on('click', 'li', function () {
        $("#stringValue").val($(this).text());
        UI.listItemClick();
        $(this).addClass('active');

        //Checking top and bot borders
        if ($(this).index() + 1 >= $("#dataList li").size()) {
            $('#btn-move-down').prop("disabled", true);
        }
        else {
            $('#btn-move-down').prop("disabled", false);
        }
        if ($(this).index() == 0) {
            $('#btn-move-up').prop("disabled", true);
        }
        else {
            $('#btn-move-up').prop("disabled", false);
        }
    });

    $("#btn-delete").click(function () {
        ui.delete();
    });

    $("#btn-move-up").click(function () {
        ui.moveUp();
        UI.dissableControls()
    });

    $("#btn-move-down").click(function () {
        ui.moveDown();
        UI.dissableControls()
    });


    $("#data-form").submit(function (event) {
        ui.submitFormAction();
        this.reset();
    });

    //Uncoment this ti activate disabling activation
    //$(document).click(function(e){
    //    $('#dataList li').removeClass('active');
    //    dissableControls()

    //});

});
