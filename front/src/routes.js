import Vue from "vue";
import VueRouter from "vue-router";
import CategoryRedirect from "@/components/CategoryRedirect";

Vue.use(VueRouter);

const isAuthenticationMember = () => (to, from, next) => {
	if (localStorage.getItem('auth_token') !== null) {
		next()
	} else {
		next('/login');
	}
};

export const routes = [
	{
		path: "/",
		component: () => import('@/components/Home'),
		name: "home"
	},
	{

		path: "/cart",
		component: () => import('@/components/cart/Cart'),
		beforeEnter: isAuthenticationMember(),
		name: "cart",
	},
	{
		path: "/login",
		component: () => import('@/components/member/Login'),
		name: "login"
	},
	{
		path: "/register",
		component: () => import('@/components/member/Register'),
		name: "register"
	},
	{
		path: "/heart",
		component: () => import('@/components/Heart/Heart'),
		beforeEnter: isAuthenticationMember(),
		name: "heart"
	},
	{
		path: "/item/:id",
		component: () => import('@/components/item/ItemDetails'),
		name: "item"
	},
	{
		path: "/categoryRedirect/:itemCategoryId",
		component: CategoryRedirect,
		name: "categoryRedirect"
	},
	{
		path: "*",
		redirect: "/"
	}
];