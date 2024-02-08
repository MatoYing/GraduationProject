var KTAppEcommerceReportViews = function() {
	var t, e;
	return {
		init: function() {
			(t = document.querySelector("#kt_ecommerce_report_views_table")) 
			&& 
			(e = $(t)
				.DataTable({
					info: !1,
					order: false,
					pageLength: 10,
				})
			)
		}
	}
}();


var KTAppEcommerceReportViews2 = function() {
	var t, e;
	return {
		init: function() {
			(t = document.querySelector("#kt_ecommerce_report_views_table2")) 
			&& 
			(e = $(t)
				.DataTable({
					info: !1,
					order: false,
					pageLength: 10,
				})
			)
		}
	}
}();



KTUtil.onDOMContentLoaded((function() {
	KTAppEcommerceReportViews.init()
}));

KTUtil.onDOMContentLoaded((function() {
	KTAppEcommerceReportViews2.init()
}));