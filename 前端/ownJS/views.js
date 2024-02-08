// "use strict";
var KTAppEcommerceReportViews = function() {

	var t, e;
	return {
		init: function() {
			(t = document.querySelector("#kt_ecommerce_report_views_table")) 
			&& 
			(e = $(t)
				.DataTable({
					info: !1,
					order: [],
					pageLength: 10,
					columnDefs: [{
						// render: DataTable.render.number(",", ".", 2),
						// targets: 4
					}, {
						orderable: !1,
						targets: 9
					}]
				}),

				(() => {
					const e = "监控报表";
					new $.fn.dataTable.Buttons(t, {
							buttons: [{
								extend: "excelHtml5",
								title: e,
								exportOptions: {
									columns: [0, 1, 2, 3, 4, 5, 6, 7, 8]
								}
							}, {
								extend: "pdfHtml5",
								title: e,
								exportOptions: {
									columns: [0, 1, 2, 3, 4, 5, 6, 7, 8]
								}
							}]
						})
						.container()
						.appendTo($("#kt_ecommerce_report_views_export")), document.querySelectorAll("#kt_ecommerce_report_views_export_menu [data-kt-ecommerce-export]")
						.forEach((t => {
							t.addEventListener("click", (t => {
								t.preventDefault();
								const e = t.target.getAttribute("data-kt-ecommerce-export");
								document.querySelector(".dt-buttons .buttons-" + e)
									.click()
							}))
						}))
				})(), 
				
				document.querySelector('[data-kt-ecommerce-order-filter="search"]')
				.addEventListener("keyup", (function(t) {
					e.search(t.target.value)
						.draw()
				})), 

				(() => {
					const t = document.querySelector('[data-kt-ecommerce-order-filter="status"]');
					$(t)
						.on("change", (t => {
							let r = t.target.value;
							"all" === r && (r = ""), e.column(4)
								.search(r)
								.draw()
						}))
				})()
			)
		}
	}
}();

// setTimeote(function(){ 
// 	KTUtil.onDOMContentLoaded((function() {
// 		console.log("views.js");
// 		KTAppEcommerceReportViews.init()
// 	}));
// }, 5000);

// KTUtil.onDOMContentLoaded((function() {
// 	console.log("views.js");
// 	KTAppEcommerceReportViews.init()
// }));