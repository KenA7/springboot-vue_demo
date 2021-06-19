import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import About from '../views/About'
import Customer from '../views/Customer.vue'
import index from '../views/index.vue'
import AddCustomer from "../views/AddCustomer.vue";
import CustomerDetails from "@/views/CustomerDetails";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Manage Customer',
    component: index,
    redirect: '/customer',
    show: true,
    children:[
      {
        path: '/customer',
        name: 'All Customers',
        component: Customer
      },
      {
        path: '/create',
        name: 'Add Customer',
        component: AddCustomer
      }
    ]
  },
  {
    path: '/details',
    name: 'Customer Details',
    component: CustomerDetails,
    show: false
  },
  {
    path: '/',
    name: 'About Us',
    component: index,
    show: true,
    redirect: '/aboutUs',
    children: [
      {
        path:'/aboutUs',
        name: 'About',
        component: About
      },
      {
        path: '/home',
        name: 'Contact us',
        component: Home
      }
    ]
  }

]


const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
})

export default router
