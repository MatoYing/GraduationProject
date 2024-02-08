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
						orderable: !1,
						targets: 8
					}]
				}),

				(() => {
					const e = "指标报警规则";
					new $.fn.dataTable.Buttons(t, {
							buttons: [{
								extend: "excelHtml5",
								title: e,
								exportOptions: {
									columns: [0, 1, 2, 3, 4, 5, 6, 7]
								}
							}, 
								{
									extend: "pdfHtml5",
									title: e,
									font: 'ShuJueWei',
									exportOptions: {
										columns: [0, 1, 2, 3, 4, 5, 6, 7],
									}
								}
							]
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


var KTAppEcommerceReportViews2 = function() {
	var t, e;
	return {
		init: function() {
			(t = document.querySelector("#kt_ecommerce_report_views_table2")) 
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
						targets: 8
					}]
				}),

				// (() => {
				// 	var t = moment()
				// 		.subtract(29, "days"),
				// 		e = moment(),
				// 		r = $("#kt_ecommerce_report_views_daterangepicker2");

				// 	function o(t, e) {
				// 		r.html(t.format("YYYY-MM-DD") + " - " + e.format("YYYY-MM-DD"))
				// 	}
				// 	// JQuery插件
				// 	r.daterangepicker({
				// 		startDate: moment().subtract(6, 'days'),  //7天前
    			// 		endDate: new Date(),                   
				// 		locale: {
				// 			"format": 'YYYY-MM-DD',
				// 			"separator": " - ",
				// 			"applyLabel": "确定",
				// 			"cancelLabel": "取消",
				// 			"resetLabel": "重置",
				// 			"fromLabel": "起始时间",
				// 			"toLabel": "结束时间'",
				// 			"customRangeLabel": "自定义",
				// 			"weekLabel": "W",
				// 			"daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
				// 			"monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
				// 			"firstDay": 1
				// 		},
				// 		ranges: {
				// 			"今天": [moment(), moment()],
				// 			"昨天": [moment()
				// 				.subtract(1, "days"), moment()
				// 				.subtract(1, "days")
				// 			],
				// 			"过去7天": [moment()
				// 				.subtract(6, "days"), moment()
				// 			],
				// 			"过去30天": [moment()
				// 				.subtract(29, "days"), moment()
				// 			],
				// 			"当月": [moment()
				// 				.startOf("month"), moment()
				// 				.endOf("month")
				// 			],
				// 			"前一月": [moment()
				// 				.subtract(1, "month")
				// 				.startOf("month"), moment()
				// 				.subtract(1, "month")
				// 				.endOf("month")
				// 			]
				// 		}
				// 	}, o), o(t, e)
				// })(),

				(() => {
					const e = "指标报警信息";
					new $.fn.dataTable.Buttons(t, {
							buttons: [{
								extend: "excelHtml5",
								title: e,
								exportOptions: {
									columns: [0, 1, 2, 3, 4, 5, 6, 7]
								}
							}, {
								extend: "pdfHtml5",
								title: e,
								// font: 'SimSun,STSong',
								exportOptions: {
									columns: [0, 1, 2, 3, 4, 5, 6, 7]
								}
							}]
						})
						.container()
						.appendTo($("#kt_ecommerce_report_views_export2")), document.querySelectorAll("#kt_ecommerce_report_views_export_menu2 [data-kt-ecommerce-export2]")
						.forEach((t => {
							t.addEventListener("click", (t => {
								t.preventDefault();
								const e = t.target.getAttribute("data-kt-ecommerce-export2");
								document.querySelector(".dt-buttons .buttons-" + e)
									.click()
							}))
						}))
				})(), 
				
				document.querySelector('[data-kt-ecommerce-order-filter2="search"]')
				.addEventListener("keyup", (function(t) {
					e.search(t.target.value)
						.draw()
				})), 

				(() => {
					const t = document.querySelector('[data-kt-ecommerce-order-filter2="status"]');
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

// KTUtil.onDOMContentLoaded((function() {
// 	KTAppEcommerceReportViews.init()
// }));

// KTUtil.onDOMContentLoaded((function() {
// 	// 不知道为什么一个里面只能放一个
// 	KTAppEcommerceReportViews2.init();
// }));