var KTAppCalendar = function() {
	var e, t, n, a, o, r, i, l, d, c, s, m, u, v, f, p, y, D, k, _, g, b, S, h, T, Y, w, L, E, M = {
		id: "",
		eventName: "",
		eventDescription: "",
		startDate: "",
		endDate: "",
		allDay: !1
	};
		B = () => {
			var e, t, n;
			w.show(), M.allDay ? (
                e = "", 
                t = moment(M.startDate).format("YYYY-MM-DD"), 
                n = moment(M.endDate).format("YYYY-MM-DD")) : 
                (e = "", t = moment(M.startDate).format("YYYY-MM-DD"), 
                n = moment(M.endDate).format("YYYY-MM-DD")), 
                g.innerText = M.eventName, 
                b.innerText = e, 
                S.innerText = M.eventDescription ? M.eventDescription : "--", 
                h.innerText = M.eventLocation ? M.eventLocation : "--", 
                T.innerText = t, Y.innerText = n
		},
		
		N = e => {
			M.id = e.id, M.eventName = e.title, M.eventDescription = e.description, M.eventLocation = e.location, M.startDate = e.startStr, M.endDate = e.endStr, M.allDay = e.allDay
		}
	return {
		init: function(ceshi) {
			const H = document.getElementById("kt_modal_view_event");
			var F, O, I, R, V, P;
			w = new bootstrap.Modal(H), 
            g = H.querySelector('[data-kt-calendar="event_name"]'), 
            b = H.querySelector('[data-kt-calendar="all_day"]'), 
            S = H.querySelector('[data-kt-calendar="event_description"]'), 
            h = H.querySelector('[data-kt-calendar="event_location"]'), 
            T = H.querySelector('[data-kt-calendar="event_start_date"]'), 
            Y = H.querySelector('[data-kt-calendar="event_end_date"]'), 
            L = H.querySelector("#kt_modal_view_event_edit"), 
            E = H.querySelector("#kt_modal_view_event_delete"), 
            F = document.getElementById("kt_calendar_app"), 
            O = moment().startOf("day"), 
            I = O.format("YYYY-MM"), 
            R = O.clone().subtract(1, "day").format("YYYY-MM-DD"), 
            V = O.format("YYYY-MM-DD"), 
            P = O.clone().add(1, "day").format("YYYY-MM-DD"), 
            (e = new FullCalendar.Calendar(F, {
					headerToolbar: {
						left: "prev,next today",
						center: "title",
						right: ""
					},
					initialDate: V,
                    locale: 'zh-cn',
					navLinks: !0,
					selectable: !0,
					selectMirror: !0,
					// select: function(e) {
					// 	N(e), x()
					// },
					eventClick: function(e) {
						N({
							id: e.event.id,
							title: e.event.title,
							description: e.event.extendedProps.description,
							location: e.event.extendedProps.location,
							startStr: e.event.startStr,
							endStr: e.event.endStr,
							allDay: e.event.allDay
						}), B()
					},
					editable: false,
					dayMaxEvents: !0,
					events: ceshi,
					datesSet: function() {}
				}))
				.render()
		}
	}
}();


// var ceshi = [{
//     title: "测试",
//     start: "2023-04-24",
//     end: "2023-04-24" + "+01",
//     description: "测试",
//     location: "测试"
// }]

var ceshi = [];

var getData = function(ceshi) {
    this.ceshi = ceshi;
};


// KTUtil.onDOMContentLoaded((function() {
// 	KTAppCalendar.init(ceshi)
// }));

setTimeout(function() {
	console.log("dsdds");
	KTUtil.onDOMContentLoaded((function() {
		console.log(this.ceshi);
		KTAppCalendar.init(ceshi)
	}));
}, 2000);
