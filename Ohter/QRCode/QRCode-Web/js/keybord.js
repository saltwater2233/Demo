var Keybord = (function(){
    //合并对象
    var extend = (function(){
        var protoprops = ['toString','valueOf',
            'constructor','hasOwnProperty','isPrototypeOf',
            'propertyIsEnumerable','toLocaleString'];

        for(var p in {toString:null}){
            return function extend(o){
                for(var i = 1;i<arguments.length;i++){
                    var source = arguments[i];
                    for(var prop in source)
                        o[prop] = source[prop];
                }
                return o;
            };
        }

        return function patched_extend(o){
            for(var i = 1; i<arguments.length;i++){
                var source = arguments[i];
                for(var prop in source)
                    o[prop] = source[prop];
                for(var j = 0; j<protoprops.legnth;j++){
                    prop = protoprops[j];
                    if(source.hasOwnProperty(prop))
                        o[prop] = source[prop];
                }
            }
            return o;
        };
    }());
    //阻止冒泡
    function stopBubble(e){
        if(e&&e.stopPropagation){//非IE
            e.stopPropagation();
        }
        else{//IE
            window.event.cancelBubble=true;
        }
    }
    function Keybord(){}
    Keybord.fn = Keybord.prototype = {
        init:function(obj){
            var me = this;
            // console.log(me);
            extend(me.setting,obj);
            // console.log(setting);
            me.rander();
        },
        setting:{
            //默认显示
            defaultstr:'浙B',
            //默认输入框输入状态框的位置
            defaultinput:3,
            regex:{
                car7:/^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/,
                car8:/^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{5}[A-Z0-9挂学警港澳]{1}$/
            },
            //input容器
            input:'#inputs',
            inputnum:8,
            //键盘容器
            keybord:'#keybord',
            //按键：一个数组一个键盘
            keys:[[
                '京','津','沪','渝','冀','豫','云','辽','黑','湘','皖','鲁','新','苏','浙','赣','鄂','桂','甘','晋','蒙','陕','吉','闽','贵','粤','青','藏','川','宁','琼','使','领'
            ],[
                '1','2','3','4','5','6','7','8','9','0','Q','W','E','R','T','Y','U','P','A','S','D','F','G','H','J','K','L','Z','X','C','V','B','N','M','学','港','澳'
            ]],
            errmsg:'请输入正确的车牌号',
            callback:function(iscarnum){
                console.log(setting.carnum)
            },
            carnum:''
        },
        //渲染
        rander:function(){
            var me = this;
            //渲染键盘
            for(var i=0;i<setting.keys.length;i++){
                var itm = setting.keys[i];
                var div = $('<div class="kb'+(i+1)+'">');
                for(var k=0;k<itm.length;k++){
                    var item = itm[k];
                    var attr = '';
                    var numregex = /[1234567890]/;
                    var wordregex = /[A-Za-z]/i;
                    // debugger;
                    if(item.search(numregex)!=-1){
                        attr = 'num';
                    }else if(item.search(wordregex)!=-1){
                        attr = 'word';
                    }else{
                        attr = 'zi'
                    }
                    var html = '<div class="item" data-attr="'+attr+'" data-isclick="true">' +
                        '<div class="keys" '+((item=='浙'||item=='B')?('style="font-weight:bold"'):'')+'>'+item+'</div>' +
                        '</div>';
                    div.append(html);
                }
                div.append($('<div class="item" data-attr="close"><div class="keys">删除</div></div>'))
                // div.append($('<div class="item" data-attr="empty"><div class="keys">清空</div></div>'))
                $(setting.keybord).append(div);
            }

            //给键盘按键添加点击事件
            me.keyclick();
            // 渲染输入框
            for(var inputi=0;inputi<setting.inputnum;inputi++){
                var div = $('<div data-index="'+(inputi+1)+'" class="item">')
                if(inputi == 7){
                    div.addClass('new');
                }
                if((inputi+1) == setting.defaultinput){
                    div.addClass('on');
                }
                $(setting.input).append(div);
                if(inputi == 1){
                    $(setting.input).append($('<div class="dian">'))
                }
            }
            //给input添加点击事件
            me.inputclick();
            // 渲染默认字
            for(var ki = 0;ki<setting.defaultstr.length;ki++){
                var item = setting.defaultstr[ki];
                $(setting.input+' [data-index="'+(ki+1)+'"]').html(item);
            }
            //默认选中和默认显示哪个键盘
            Keybord.fn.clickdocuemnt();
            (function(){
                $(setting.input+' [data-attr="'+setting.defaultstr.length+'"]').addClass('on')
                Keybord.fn.showkeybord(setting.defaultstr.length+1);
            })()
        },
        //哪个键盘
        howkeybord:function(i){
            if(i == 1){
                $(setting.keybord+' .kb1').addClass('on').siblings('.on').removeClass('on')
            }else{
                $(setting.keybord+' .kb2').addClass('on').siblings('.on').removeClass('on')
            }
        },
        //显示键盘
        showkeybord:function(i){
            var me = this;
            // console.log(i)
            $(setting.keybord).show();
            Keybord.fn.howkeybord(i);
            //全部可点
            if(i == 1||i == 7||i == 8){
                $(setting.keybord+' .item.no').removeClass('no')
                //字母可点
            }else if(i == 2){
                $(setting.keybord+' .item.no').removeClass('no');
                $(setting.keybord+' [data-attr="num"],'+setting.keybord+' [data-attr="zi"]').addClass('no')
                //数字和字母可点
            }else{
                $(setting.keybord+' .item.no').removeClass('no')
                $(setting.keybord+' [data-attr="zi"]').addClass('no')
            }
        },
        //隐藏键盘
        hidekeybord:function(){
            $(setting.input+' .item.on').removeClass('on');
            $(setting.keybord).hide();
        },
        //点击按键的时候
        keyclick:function(){
            // $(setting.keybord+' .item').on('click',clickkey);
            $(setting.keybord+' .item').on('touchstart',clickkey);
            function clickkey(e){
                stopBubble(e);
                // console.log(e);
                // alert(this.innerText);
                // console.log(this.innerText);
                var text = this.innerText;
                var it = $(setting.input+' .item.on');
                var index = it.attr('data-index');
                //如果是删除
                if($(this).attr('data-attr') == 'close') {
                    //清除内容
                    it.text('');
                    if(index == setting.inputnum){
                        it.addClass('new');
                        it.removeClass('on').prev().addClass('on')
                    }else{
                        if(it.next().className == 'dian'){
                            //判断是否切换
                            if(it.next().next().text().trim()==''){
                                it.removeClass('on').prev().addClass('on')
                            }
                        }else{
                            //判断是否切换
                            if(it.next().text().trim()==''){
                                if(index!=1) {
                                    if (it.prev()[0].className == 'dian') {
                                        it.removeClass('on').prev().prev().addClass('on')
                                    } else {
                                        it.removeClass('on').prev().addClass('on')
                                    }
                                }
                            }
                        }
                    }

                    //判断是否组成车牌号
                    Keybord.fn.iscarnum();
                    //显示键盘
                    Keybord.fn.showkeybord($(setting.input+' .item.on').attr('data-index'));

                }else{
                    if(!$(this).hasClass('no')) {
                        it.text(text);
                        if (index != 8) {
                            if (it.next()[0].className != 'dian') {
                                it.removeClass('on').next().addClass('on');
                            } else {
                                it.removeClass('on').next().next().addClass('on');
                            }
                            Keybord.fn.showkeybord(Number(index) + 1);
                        } else {
                            it.removeClass('on').removeClass('new');
                            Keybord.fn.hidekeybord();
                        }
                    }
                }
                //判断是否组成车牌号
                Keybord.fn.iscarnum();
            }
        },
        //input 点击事件
        inputclick:function(){
            var me = this;
            // $(setting.input+' .item').on('click',clicktap);
            $(setting.input+' .item').on('touchstart',clicktap);
            //点击input之后的执行函数
            function clicktap(e){
                stopBubble(e);
                if(!$(this).hasClass('on')) {
                    var index = $(this).attr('data-index');
                    $(this).addClass('on').siblings('.item').removeClass('on');
                    // debugger;
                    Keybord.fn.showkeybord(index);
                }
            }
        },
        //判断是否组成车牌号
        iscarnum:function(){
            var result='',str = '';
            $(setting.input+' .item').each(function(i,e){
                if(i<7){
                    if(e.innerText.trim() == ''){
                        str = -1;
                    }else{
                        str += e.innerText.trim();
                    }
                }else{
                    str+=e.innerText;
                }
            });

            if(str.length == 7){
                result = setting.regex.car7.test(str)
            }else if(str.length == 8){
                result = setting.regex.car8.test(str)
            }
            if(result == true){
                setting.carnum = str;
                setting.callback(true,str);
            }else{
                setting.callback(false)
            }
        },
        // document点击
        clickdocuemnt:function(){
            $('body').on('touchstart',function(e){
                Keybord.fn.hidekeybord();
            })
        }
    }

    var setting = Keybord.prototype.setting;

    return Keybord;
})()