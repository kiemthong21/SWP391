$(document).ready(function () {
    var x_timer;
    $("#email").keyup(function (e) {
        clearTimeout(x_timer);
        var email = $(this).val();
        x_timer = setTimeout(function () {
            check_email_ajax(email);
        }, 1000);
    });

    $("#password").keyup(function (e)
    {
        clearTimeout(x_timer);
        var password = $("#password").val();
        x_timer = setTimeout(function () {
            check_valid_password(password);
        }, 1000);
    });

    $("#repassword").keyup(function (e)
    {
        clearTimeout(x_timer);
        var password = $("#password").val();
        var repassword = $("#repassword").val();
        x_timer = setTimeout(function () {
            check_password(password, repassword);
        }, 1000);
    });

    $('#phone').keyup(function (e) {
        clearTimeout(x_timer);
        var phone = $("#phone").val();
        x_timer = setTimeout(function () {
            check_phone(phone);
        }, 1000);
    });

    $('#dob').change(function (e) {
        clearTimeout(x_timer);
        x_timer = setTimeout(function () {
            regBtn();
        }, 1000);
    });
    
    

    function check_email_ajax(email) {
        var email = $('#email').val();
        $.ajax({
            url: 'EmailValidate',
            method: 'POST',
            data: {
                email: email
            },
            success: function (resultText) {
                $('#alert').html(resultText);
            },
            error: function (jqXHR, exception) {
                $('#alert').addClass('alert alert-danger');
                $('#alert').html("Please Fill All Field!");
            }
        });
    }

    $("#mail").keyup(function (e) {
        clearTimeout(x_timer);
        x_timer = setTimeout(function () {
            check_user_ajax();
        }, 500);
    });

    $("#pass").keyup(function (e) {
        clearTimeout(x_timer);
        x_timer = setTimeout(function () {
            check_user_ajax();
        }, 500);
    });

    function check_user_ajax() {
        var mail = $('#mail').val();
        var pass = $('#pass').val();
        $.ajax({
            url: 'LoginValidate',
            method: 'POST',
            data: {
                mail: mail,
                pass: pass
            },
            success: function (resultText) {
                $('#alertt').html(resultText);
            },
            error: function (jqXHR, exception) {
                $('#alertt').addClass('alert alert-danger');
                $('#alertt').html("Please Fill All Field!");
            }
        });
    }
    
    function check_valid_password(password) {
        var validPass = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/.test(password)
        if (!validPass) {
            $('#vpassalert').html('<div class="alert alert-danger d-flex align-items-center"><svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg><div>Your password must have at least 8 character and contain at least one letter or digit, one upper letters!</div></div>');
        } else {
            $('#vpassalert').html('<div class="alert alert-success d-flex align-items-center"><svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg><div>Valid Password</div></div>');
        }
    }

    function check_password(password, repassword) {
        if (password != repassword) {
            $('#passalert').html('<div class="alert alert-danger d-flex align-items-center"><svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg><div>Password and confirm password not correct!</div></div>');       
        } else {
            $('#passalert').html('<div class="alert alert-success d-flex align-items-center"><svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg><div>Valid Password</div></div>');
        }
    }

    function check_phone(phone) {
        var isphone = /^(1\s|1|)?((\(\d{3}\))|\d{3})(\-|\s)?(\d{3})(\-|\s)?(\d{4})$/.test(phone);
        if (!isphone) {
            $('#phonealert').html('<div class="alert alert-danger d-flex align-items-center"><svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg><div>Please enter valid phone number!</div></div>');
        } else {
            $('#phonealert').html('<div class="alert alert-success d-flex align-items-center"><svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg><div>Valid Phonenumber</div></div>');
        }
    }

    function regBtn() {
        $('#regBtn').html('<button type="submit" class="btn btn-success btn-block btn-lg gradient-custom-4 text-body mb-2">Register</button>');
    }

});


