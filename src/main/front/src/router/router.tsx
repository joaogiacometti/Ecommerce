import { createBrowserRouter } from "react-router-dom";
import NotFoundPage from "@/views/NotFoundPage.tsx";
import HomePage from "@/views/HomePage.tsx";
import ProductsPage from "@/views/ProductsPage.tsx";
import LoginPage from "@/views/LoginPage";
import RegisterPage from "@/views/RegisterPage";
import ForgotPasswordPage from "@/views/ForgotPasswordPage";

const router = createBrowserRouter([
  {
    id: "home",
    path: "/",
    element: <HomePage />,
  },
  {
    id: "products",
    path: "/products",
    element: <ProductsPage />,
  },
  {
    id: "register",
    path: "/register",
    element: <RegisterPage />,
  },
  {
    id: "login",
    path: "/login",
    element: <LoginPage />,
  },
  {
    id: "forgot-password",
    path: "/forgot-password",
    element: <ForgotPasswordPage />,
  },
  {
    path: "*",
    element: <NotFoundPage />,
  },
]);

export default router;
