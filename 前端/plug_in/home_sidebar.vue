<template>
	<div id="kt_app_sidebar" class="app-sidebar flex-column" data-kt-drawer="true" data-kt-drawer-name="app-sidebar" data-kt-drawer-activate="{default: true, lg: false}" data-kt-drawer-overlay="true" data-kt-drawer-width="225px" data-kt-drawer-direction="start">
		<div class="app-sidebar-logo px-6" id="kt_app_sidebar_logo">
			<a href="/index.html">
				<img alt="Logo" src="/assets/media/logos/default-dark.svg" class="h-40px app-sidebar-logo-default" style="width: 160px;"/>
				<img alt="Logo" src="/assets/media/logos/default-small.svg" class="h-30px app-sidebar-logo-minimize" />
			</a>
		</div>
		<div class="app-sidebar-menu scroll" style="overflow-x:hidden">
			<div id="kt_app_sidebar_menu_wrapper" class="app-sidebar-wrapper hover-scroll-overlay-y my-5" data-kt-scroll="true" data-kt-scroll-activate="true" data-kt-scroll-height="auto" data-kt-scroll-dependencies="#kt_app_sidebar_logo, #kt_app_sidebar_footer" data-kt-scroll-wrappers="#kt_app_sidebar_menu" data-kt-scroll-offset="5px" data-kt-scroll-save-state="true">
				<div class="menu menu-column menu-rounded menu-sub-indention px-3" id="#kt_app_sidebar_menu" data-kt-menu="true" data-kt-menu-expand="false">
					<div class="menu-item">
						<a class="menu-link" href="/index.html">
							<span class="menu-icon">
								<i class="bi bi-speedometer2" style="font-size: 1.5rem;"></i>
							</span>
							<span class="menu-title">监控概况</span>
						</a>
					</div>

					<div class="menu-item pt-5">
						<div class="menu-content">
							<span class="menu-heading fw-bold text-uppercase fs-7">监控模块</span>
						</div>
					</div>

					<div class="menu-item">
						<a class="menu-link" href="/monitor_module/metric_monitor.html">
							<span class="menu-icon">
								<i class="bi bi-1-square" style="font-size: 1.5rem;"></i>
							</span>
							<span class="menu-title">指标监控</span>
						</a>
					</div>

					<div class="menu-item">
						<a class="menu-link" href="/monitor_module/log_monitor.html">
							<span class="menu-icon">
								<i class="bi bi-2-square" style="font-size: 1.5rem;"></i>
							</span>
							<span class="menu-title">日志监控</span>
						</a>
					</div>


					<div class="menu-item">
						<a class="menu-link" href="/monitor_module/file_monitor.html">
							<span class="menu-icon">
								<i class="bi bi-3-square" style="font-size: 1.5rem;"></i>
							</span>
							<span class="menu-title">文件监控</span>
						</a>
					</div>




					<div class="menu-item pt-5">
						<div class="menu-content">
							<span class="menu-heading fw-bold text-uppercase fs-7">个人模块</span>
						</div>
					</div>

					<div class="menu-item">
						<a class="menu-link" href="/person_module/person.html">
							<span class="menu-icon">
								<i class="bi bi-universal-access" style="font-size: 1.5rem;"></i>
							</span>
							<span class="menu-title">个人看板</span>
						</a>
					</div>

					<div class="menu-item">
						<a class="menu-link" href="/person_module/task.html">
							<span class="menu-icon">
								<i class="bi bi-bookmark-check" style="font-size: 1.5rem;"></i>
							</span>
							<span class="menu-title">任务管理</span>
						</a>
					</div>

					<div class="menu-item">
						<a class="menu-link" href="/person_module/calendar.html">
							<span class="menu-icon">
								<i class="bi bi-calendar3-event" style="font-size: 1.5rem;"></i>
							</span>
							<span class="menu-title">日程安排</span>
						</a>
					</div>





					<div class="menu-item pt-5">
						<div class="menu-content">
							<span class="menu-heading fw-bold text-uppercase fs-7">辅助模块</span>
						</div>
					</div>

					<div class="menu-item">
						<a class="menu-link" href="/assist_module/report.html">
							<span class="menu-icon">
								<i class="bi bi-card-text" style="font-size: 1.5rem;"></i>
							</span>
							<span class="menu-title">报表</span>
						</a>
					</div>

					<div class="menu-item">
						<a class="menu-link" href="/assist_module/knowledge.html">
							<span class="menu-icon">
								<i class="bi bi-files" style="font-size: 1.5rem;"></i>
							</span>
							<span class="menu-title">通知</span>
						</a>
					</div>

					<div class="menu-item">
						<a class="menu-link" href="/bigScreen/index.html" target="_blank">
							<span class="menu-icon">
								<i class="bi bi-easel2" style="font-size: 1.5rem;"></i>
							</span>
							<span class="menu-title">展示看板</span>
						</a>
					</div>

					<div class="menu-item">
						<a class="menu-link" href="/assist_module/help.html">
							<span class="menu-icon">
								<i class="bi bi-exclamation-octagon" style="font-size: 1.5rem;"></i>
							</span>
							<span class="menu-title">帮助中心</span>
						</a>
					</div>





					<div class="menu-item pt-5">
						<div class="menu-content">
							<span class="menu-heading fw-bold text-uppercase fs-7">其他</span>
						</div>
					</div>

					<div class="menu-item">
						<a class="menu-link" href="https://github.com/MatoYing/LastProject" target="_blank">
							<span class="menu-icon">
								<i class="bi bi-code-square" style="font-size: 1.5rem;"></i>
							</span>
							<span class="menu-title">代码仓库</span>
						</a>
					</div>

					<div class="menu-item">
						<a class="menu-link" href="http://127.0.0.1:8088/swagger-ui/index.html#/" target="_blank">
							<span class="menu-icon">
								<i class="bi bi-braces-asterisk" style="font-size: 1.5rem;"></i>
							</span>
							<span class="menu-title">接口文档</span>
						</a>
					</div>

				</div>
			</div>
		</div>
	</div>
</template>

<script>
</script>

<style scoped>
</style>