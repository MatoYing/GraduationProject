
var KTModalNewTarget = function() {
	var a;
	return {
		init: function() {
            a = document.querySelector("#kt_modal_new_target_form");
            
            $(a.querySelector('[name="due_date"]')).flatpickr({
                // locale: "zh-cn",
				// enableTime: !0,
				// dateFormat: "d, M Y"
                locale: {
                    // "months": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
                    // "firstDay": 1
                    months: {
                        shorthand: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
                        longhand: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"]
                    },
                    weekdays: {
                        shorthand: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
                        longhand: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"]
                    },
                },
			})
                
                
		}
	}
}();
KTUtil.onDOMContentLoaded((function() {
	KTModalNewTarget.init()
}));