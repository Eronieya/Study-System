// 点击隐藏按钮， 隐藏列表， 更换按钮内容。
$(function () {
    $("#hide").click(function () {
        if ($("#hide").attr("val") == "hide") {
            $("#menu").animate({left: '-200px'});
            $("#hide a").css({
                background: "url(/img/next.png) no-repeat",
                backgroundSize: "40px 40px"
            }).parent().attr("val", "out");
        } else {
            $("#menu").animate({left: '46px'});
            $("#hide a").css({
                background: "url(/img/back.png) no-repeat",
                backgroundSize: "40px 40px"
            }).parent().attr("val", "hide");
        }
    })

    // 隐藏按钮选定时透明度的改变
    $("#hide").hover(function () {
        $("#hide a").fadeTo(1,0.5);
    },function () {
        $("#hide a").fadeTo(1,1);
    })

    // 点击面板隐藏键
    // 左侧
    $(".hide-left a").on("click", function () {
        $(".left-box").css({
            left: "-700px"
        });
        $(".class-info-box").fadeIn("1s");
        $(this).parent().siblings().fadeOut("1s");
    });

    // 点击面板隐藏键
    // 右侧
    $(".hide-right a").on("click", function () {
        $(".right-box").css({
            right: "-700px"
        });
        $(".exm-info-box").fadeIn("1s");
        $(this).parent().siblings().fadeOut("1s");
    });

    // 点击相应框内图片时触发 此处给所有的a都添加监听事件
    // 监听左框
    $(".my-info a").on("click", function () {
        console.log($(this).attr("message"));
        // 判断用户点击哪一个按钮 相应做出不同的显示
        if ( $(this).attr("message") == 'myClass') {
            $(".left-box").css({
                left: "100px",
                translation: "1s"
            });
            $(".class-info-box").fadeOut("1s",function () {
                $(".my-class").fadeIn(1);
            });

        } else if ( $(this).attr("message") == 'myWork' ) {
            $(".left-box").css({
                left: "100px",
                translation: "1s"
            });
            $(".class-info-box").fadeOut("1s",function () {
                $(".my-work").fadeIn(1);
            });

        } else if ( $(this).attr("message") == 'myMissSign' ) {
            $(".left-box").css({
                left: "100px",
                translation: "1s"
            });
            $(".class-info-box").fadeOut("1s",function () {
                $(".my-miss-sign").fadeIn(1);
            });

        }
    });

    // 点击相应框内图片时触发 此处给所有的a都添加监听事件
    // 监听右框
    $(".my-info a").on("click", function () {
        console.log($(this).attr("message"));
        // 判断用户点击哪一个按钮 相应做出不同的显示
        if ( $(this).attr("message") == 'myExam') {
            $(".right-box").css({
                right: "100px",
                translation: "1s"
            });
            $(".exm-info-box").fadeOut("1s",function () {
                $(".my-exam").fadeIn(1);
            });

        } else if ( $(this).attr("message") == 'waitMe' ) {
            $(".right-box").css({
                right: "100px",
                translation: "1s"
            });
            $(".exm-info-box").fadeOut("1s",function () {
                $(".wait-me").fadeIn(1);
            });

        } else if ( $(this).attr("message") == 'myBad' ) {
            $(".right-box").css({
                right: "100px",
                translation: "1s"
            });
            $(".exm-info-box").fadeOut("1s",function () {
                $(".my-bad").fadeIn(1);
            });

        }
    })

    let user = {
        username: '',
        name: '',
        sex: '',
        date: '',
        identity: '',
        school: '',
        major: ''
    }

    $(".before-edit").on("click", function () {
        // 获取用户信息
        user.username = $(".information").eq(0).text();
        user.name = $(".information").eq(1).text();
        user.sex = $(".information").eq(2).text();
        user.date = $(".information").eq(3).text();
        user.identity = $(".information").eq(4).text();
        user.school = $(".information").eq(5).text();
        user.major = $(".information").eq(6).text();

        $("input:not([type='radio'])").eq(0).val(user.username);
        $("input:not([type='radio'])").eq(1).val(user.name);
        if (user.sex === '男') {
            $("input[type='radio']").eq(0).attr("checked", true);
        } else if (user.sex === '女') {
            $("input[type='radio']").eq(1).attr("checked", true);
        }
        $("input:not([type='radio'])").eq(2).val(user.date);
        if (user.identity === '学生') {
            $("input[type='radio']").eq(2).attr("checked", true);
        } else if (user.identity === '教师') {
            $("input[type='radio']").eq(3).attr("checked", true);
        } else if (user.identity === '其他') {
            $("input[type='radio']").eq(4).attr("checked", true);
        }
        $("input:not([type='radio'])").eq(3).val(user.school);
        $("input:not([type='radio'])").eq(4).val(user.major);

        $(".after-edit, input, label").show();
        $(this).hide();
        $(".information").hide();
    })

    $("#save").on("click", function () {
        // 点击确定按钮保存并修改页面上内容
        // 获取input的内容  $("input").eq(0).val();
        // $(".information").eq(1).text("123");
        // $("input[type='radio']:checked") 获取已选中的radio
        user.username = $("input:not([type='radio'])").eq(0).val();
        user.name = $("input:not([type='radio'])").eq(1).val();
        user.sex = $("input[type='radio']:checked").eq(0).val();
        user.date = $("input:not([type='radio'])").eq(2).val();
        user.identity = $("input[type='radio']:checked").eq(1).val();
        user.school = $("input:not([type='radio'])").eq(3).val();
        user.major = $("input:not([type='radio'])").eq(4).val();
        let i = 0;
        // 遍历user的属性 key为属性名
        for (let key in user) {
            // console.log(key);
            $(".information").eq(i).text(user[key]);
            i++;
        }
        $("input , label, .after-edit").hide();
        $(".before-edit, .information").show();
    })

    $("#cancel").on("click", function () {
        $(".after-edit, input, label").hide();
        $(".before-edit, .information").show();
    })


    // 经过my-item的事件
    $(".my-item").hover( function () {
            $(this).css({
                boxShadow: "0px 0px 5px 3px"
            })
        },
        function () {
            $(this).css({
                boxShadow: "none",
                textShadow: "none"
        })
    });

    // 经过my-item-head的事件 经过head-img的事件
    $(".my-item-head , .head-img, .class-name, .teacher").hover( function () {
            $(this).css({
                boxShadow: "0 0 2px 1px white",
                textShadow: "0 0 5px"
            })
        },
        function () {
            $(this).css({
                boxShadow: "none",
                textShadow: "none"
        })
    });


    // 隐藏添加课程面板
    $(".cancel").on("click", function () {
        $(".save").hide();
        $(".create-class").slideUp("slow");
        $(".all-my-class").show("slow");
    })

    // 显示添加课程面板
    $(".add-class").on("click", function () {
        $(".all-my-class").hide();
        $(".create-class").slideDown("slow");
        $(".save").show("slow");
    })

    // 两级菜单的实现
    $(".list").on("click", "span", function () {
        // console.log($(this).text());
        // 获取span内容
        let name = $(this).text();
        name = '#'+name; // 拼接成ul的id
        $(name).stop().slideToggle("slow");
    });

    // 菜单实现删除一行
    $(".list").on("click", ".remove", function () {
        // console.log($(this).parent().children("span").html());
        // 获取id
        let name = "#" + $(this).parent().children("span").html();
        $(this).parent().remove();
        $(name).remove();
        // $(this).parent().remove();
    });

    // 显示添加列表出来
    $(".list").on("click", ".add", function () {
        // 获取id
        let name = "#" + $(this).parent().children("span").html() + " li:last-child";
        let id = "#" + $(this).siblings("span").html();
        $(name).children(".add-part").parent().slideDown("slow");
        $(id).slideDown("slow");
    });

    // 隐藏input
    $(".list").on("click", ".btn-close", function () {
        $(this).parent().slideUp("slow");
        $(this).siblings("input").val("");
    })

    // 保存添加节
    $(".list").on("click", ".btn-check", function () {
        let input = $(this).siblings("input");
        let msg = input.val();
        let li = "<li>"+ msg +" <img src=\"/img/remove.png\" width=\"26px\" height=\"26px\" class=\"remove\">\n" +
            "<img src=\"/img/upload.png\" width=\"26px\" height=\"26px\" class=\"upload\"></li>";
        input.parent().before(li);
        input.val("");
    })

    // 显示添加章输入框
    $(".add-chapter").on("click", function () {
        $("#add-chapter").stop().slideToggle("slow");
    })
    // 隐藏添加章输入框
    $(".hide-chapter-editor").on("click", function () {
        $("#add-chapter").slideUp("slow");
    })

    // 保存添加章
    $(".save-chapter").on("click", function () {
        let input = $(this).siblings("input");
        let msg = input.val();
        let content = "<div>\n" +
            "    <img src=\"/img/remove.png\" width=\"30px\" height=\"30px\" class=\"remove\">\n" +
            "    <img src=\"/img/addto.png\" width=\"30px\" height=\"30px\" class=\"add\">\n" +
            "    <span>"+msg+"</span>\n" +
            "</div>\n" +
            "<ul class=\"second-list\" id=\""+msg+"\">\n" +
            "    <li>\n" +
            "        <img src=\"/img/close.png\" width=\"26px\" height=\"26px\" class=\"part-edit btn-close\">\n" +
            "        <img src=\"/img/check.png\" width=\"26px\" height=\"26px\" class=\"part-edit btn-check\">\n" +
            "        <input class=\"add-part\" type=\"text\">\n" +
            "    </li>\n" +
            "</ul>"
        $("#add-chapter").before(content);
        input.val("");
    })
})
