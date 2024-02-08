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





KTUtil.onDOMContentLoaded((function() {
	KTAppEcommerceReportViews.init()
}));
