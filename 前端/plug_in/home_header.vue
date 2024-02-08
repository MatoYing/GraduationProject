<template>

    <div id="kt_app_header" class="app-header">
		<div class="app-container container-fluid d-flex align-items-stretch justify-content-between" id="kt_app_header_container">
			<div class="d-flex align-items-center d-lg-none ms-n3 me-1 me-md-2" title="Show sidebar menu">
				<div class="btn btn-icon btn-active-color-primary w-35px h-35px" id="kt_app_sidebar_mobile_toggle">
					<span class="svg-icon svg-icon-2 svg-icon-md-1">
						<svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
							<path d="M21 7H3C2.4 7 2 6.6 2 6V4C2 3.4 2.4 3 3 3H21C21.6 3 22 3.4 22 4V6C22 6.6 21.6 7 21 7Z" fill="currentColor" />
							<path opacity="0.3" d="M21 14H3C2.4 14 2 13.6 2 13V11C2 10.4 2.4 10 3 10H21C21.6 10 22 10.4 22 11V13C22 13.6 21.6 14 21 14ZM22 20V18C22 17.4 21.6 17 21 17H3C2.4 17 2 17.4 2 18V20C2 20.6 2.4 21 3 21H21C21.6 21 22 20.6 22 20Z" fill="currentColor" />
						</svg>
					</span>
				</div>
			</div>

			<div class="d-flex align-items-stretch justify-content-between flex-lg-grow-1 flex-row-reverse" id="kt_app_header_wrapper">
				<div class="app-navbar flex-shrink-0">
					<div class="app-navbar-item ms-1 ms-md-3" id="kt_header_user_menu_toggle">
						<div class="cursor-pointer symbol symbol-30px symbol-md-40px" data-kt-menu-trigger="{default: 'click', lg: 'hover'}" data-kt-menu-attach="parent" data-kt-menu-placement="bottom-end">
							<img src="https://ui-avatars.com/api/?name=OS&background=0081ff&color=ffffff&rounded=false" alt="user" />
						</div>
						<div class="menu menu-sub menu-sub-dropdown menu-column menu-rounded menu-gray-800 menu-state-bg menu-state-color fw-semibold py-4 fs-6 w-275px" data-kt-menu="true">
							<div class="menu-item px-5">
								<a href="/person_module/account.html" class="menu-link px-5">个人主页</a>
							</div>
							<div class="menu-item px-5 my-1">
								<a href="/person_module/setting.html" class="menu-link px-5">账户设置</a>					
							</div>
							<div class="separator my-2"></div>
							<div class="menu-item px-5">
								<a href="/login_module/sign-in.html" class="menu-link px-5">退出</a>
							</div>
						</div>
					</div>
					<div class="app-navbar-item d-lg-none ms-2 me-n3" title="Show header menu">
						<div class="btn btn-icon btn-active-color-primary w-30px h-30px w-md-35px h-md-35px" id="kt_app_header_menu_toggle">
							<span class="svg-icon svg-icon-2 svg-icon-md-1">
								<svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
									<path d="M13 11H3C2.4 11 2 10.6 2 10V9C2 8.4 2.4 8 3 8H13C13.6 8 14 8.4 14 9V10C14 10.6 13.6 11 13 11ZM22 5V4C22 3.4 21.6 3 21 3H3C2.4 3 2 3.4 2 4V5C2 5.6 2.4 6 3 6H21C21.6 6 22 5.6 22 5Z" fill="currentColor" />
									<path opacity="0.3" d="M21 16H3C2.4 16 2 15.6 2 15V14C2 13.4 2.4 13 3 13H21C21.6 13 22 13.4 22 14V15C22 15.6 21.6 16 21 16ZM14 20V19C14 18.4 13.6 18 13 18H3C2.4 18 2 18.4 2 19V20C2 20.6 2.4 21 3 21H13C13.6 21 14 20.6 14 20Z" fill="currentColor" />
								</svg>
							</span>
						</div>
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
