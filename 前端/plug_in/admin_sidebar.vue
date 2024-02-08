<template>
	<div id="kt_app_sidebar" class="app-sidebar flex-column" data-kt-drawer="true" data-kt-drawer-name="app-sidebar" data-kt-drawer-activate="{default: true, lg: false}" data-kt-drawer-overlay="true" data-kt-drawer-width="225px" data-kt-drawer-direction="start">
		<div class="app-sidebar-logo px-6" id="kt_app_sidebar_logo">
			<a href="#">
				<img alt="Logo" src="/assets/media/logos/default-dark.svg" class="h-40px app-sidebar-logo-default" style="width: 160px;"/>
				<img alt="Logo" src="/assets/media/logos/default-small.svg" class="h-30px app-sidebar-logo-minimize" />
			</a>
		</div>
		<div class="app-sidebar-menu scroll" style="overflow-x:hidden">
			<div id="kt_app_sidebar_menu_wrapper" class="app-sidebar-wrapper hover-scroll-overlay-y my-5" data-kt-scroll="true" data-kt-scroll-activate="true" data-kt-scroll-height="auto" data-kt-scroll-dependencies="#kt_app_sidebar_logo, #kt_app_sidebar_footer" data-kt-scroll-wrappers="#kt_app_sidebar_menu" data-kt-scroll-offset="5px" data-kt-scroll-save-state="true">
				<div class="menu menu-column menu-rounded menu-sub-indention px-3" id="#kt_app_sidebar_menu" data-kt-menu="true" data-kt-menu-expand="false">
					<div class="menu-item">
						<a class="menu-link" href="/administer/people.html">
							<span class="menu-icon">
								<i class="bi bi-people" style="font-size: 1.5rem;"></i>
							</span>
							<span class="menu-title">人员管理</span>
						</a>
					</div>
                    <div class="menu-item">
						<a class="menu-link" href="/administer/server.html">
							<span class="menu-icon">
								<i class="bi bi-hdd" style="font-size: 1.5rem;"></i>
							</span>
							<span class="menu-title">服务器监控</span>
						</a>
					</div>
                    <div class="menu-item">
						<a class="menu-link" href="/administer/topology.html">
							<span class="menu-icon">
								<i class="bi bi-bezier" style="font-size: 1.5rem;"></i>
							</span>
							<span class="menu-title">拓扑图</span>
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