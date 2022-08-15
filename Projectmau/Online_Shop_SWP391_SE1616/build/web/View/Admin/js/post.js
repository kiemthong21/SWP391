
const parseParams = (querystring) => {
    // parse query string
    const params = new URLSearchParams(querystring);
    const obj = {};
    // iterate over all keys
    for (const key of params.keys()) {
        if (params.getAll(key).length > 1) {
            obj[key] = params.getAll(key);
        } else {
            obj[key] = params.get(key);
        }
    }
    return obj;
};

const url_string = window.location.href;
const url = new URL(url_string);
const search = url.searchParams.get("q");
const paginationLinks = document.querySelectorAll(".page-link");
if (paginationLinks) {
    paginationLinks.forEach(item => {
        var search = window.location.search;
        const params = parseParams(search);
        const page = item.getAttribute("data");
        const {code, message, ...newParams} = params;
        newParams.page = page;
        const href = encodeURI(decodeURIComponent($.param(newParams)).toString().replace(/[[]]/ig, ""));
        item.setAttribute("href", "?" + href);
    })
}

const filterHandle = (field, element) => {
    var search = window.location.search;
    const params = parseParams(search);
    const {code, message, page, ...newParams} = params;
    newParams[field] = $(element).val();
    window.location.search = encodeURI(decodeURIComponent($.param(newParams)).toString().replace(/[[]]/ig, ""));
}


const sortHandleColumn = (sort, element, idShow) => {
    var search = window.location.search;
    const params = parseParams(search);
    const {code, message, ...newParams} = params;

    if (newParams.sort == undefined) {
        newParams.sort = [];
    } else if (typeof newParams.sort == 'string' || newParams.sort instanceof String) {
        newParams.sort = [newParams.sort];
    }

    const map = new Map();
    newParams.sort.forEach(item => {
        if (typeof item == 'string' || item instanceof String) {
            var items = item.split("_");
            if (items.length > 1) {
                map[items[0]] = items[1];
            }
        }
    })

    const regex = /^[a-zA-Z]+_(ASC|DESC)$/;
    if (regex.test(sort)) {
        var sorts = sort.split("_");
        if (sorts.length > 1) {
            map[sorts[0]] = sorts[1];
        }
    }

    newParams.sort = [];
    for (let key of Object.keys(map)) {
        const value = map[key];
        newParams.sort.push(key + "_" + value);
    }

    $(element).addClass("hidden");
    $(`#${idShow}`).removeClass("hidden");
    window.location.search = encodeURI(decodeURIComponent($.param(newParams)).toString().replace(/[[]]/ig, ""));
}


const filterHandleColumn = (element) => {
    var field = $(element).attr("name");
    var search = window.location.search;
    const params = parseParams(search);
    const {code, message, page, ...newParams} = params;
    newParams[field] = [];

    $(`input[name='${field}']:checked`).each(function () {
        newParams[field].push($(this).val());
    })

    if ($(element).is(":checked")) {
        $(`input[parent='${$(element).val()}']`).each(function () {
            newParams[field].push($(this).val());
        });
    }else{
        $(`input[parent='${$(element).val()}']`).each(function () {
            newParams[field] = newParams[field].filter(item => item!= $(this).val());
        });
        newParams[field] = newParams[field].filter(item => item!= $(element).attr("parent"));
    }
    
    if($(element).attr("parent") &&
            $(`input[parent='${$(element).attr("parent")}']:checked`).length == $(`input[parent='${$(element).attr("parent")}']`).length ){
        newParams[field].push($(element).attr("parent"));
    }

    let sets = new Set(newParams[field]);
    newParams[field] = Array.from(sets);

    window.location.search = encodeURI(decodeURIComponent($.param(newParams)).toString().replace(/[[]]/ig, ""));
}


const removeFilter = (field, value) => {
    var search = window.location.search;
    const params = parseParams(search);
    const {code, message, page, ...newParams} = params;
    if (typeof newParams[field] == 'string' || newParams[field] instanceof String) {
        delete newParams[field];
    }else{       
        newParams[field] = newParams[field]?.filter(item => item !=value);
    }
    window.location.search = encodeURI(decodeURIComponent($.param(newParams)).toString().replace(/[[]]/ig, ""));
}


const searchItemFilter = (field, element) => {
    event.preventDefault();
    const val = $(element).val();
    var html = "";
    $(`.item-${field}`).each(function () {
        const text = $(this).text();
        if (text.toString().trim().toUpperCase().includes(val.toString().trim().toUpperCase())) {
            $(this).parent().removeClass("hidden");
        } else {
            $(this).parent().addClass("hidden");
        }
    });
}

$("#search-category").keypress(function (e) {
    if (e.which == 13) {
        e.preventDefault();
    }
})

$(function () {
    $("#filter-drag-content").draggable({
        cursor: "move",
        start: function () {
            $("#button-open-filter").removeClass("hidden");
            $("#button-open-filter").removeClass("md:hidden");
            $("#button-close-filter").removeClass("hidden");
            $("#filter-drag-layout").addClass(`fixed top-[122px] right-[20px]`);
        },
    });
});


const showFilterbar = (element) => {
        $("#button-open-filter").removeClass("md:hidden");
    if ($("#filter-drag-layout").hasClass("hidden")) {
        $("#button-close-filter").removeClass("hidden");
        $("#filter-drag-layout").removeClass("hidden");
        $("#filter-drag-content").removeClass("hidden");
        $("#filter-drag-layout").addClass(`fixed top-[122px] right-[20px]`);
        $("#filter-drag-content").css({'top': -10, 'left' : 0});
    } else {
        $("#button-close-filter").addClass("hidden");
        $("#filter-drag-layout").addClass("hidden");
        $("#filter-drag-content").addClass("hidden");
    }
}

const closeFilter = () => {
    $("#button-open-filter").removeClass("md:hidden");
    $("#button-open-filter").removeClass("hidden");
    $("#filter-drag-content").addClass("hidden");
    $("#button-close-layout").addClass("hidden");
}



