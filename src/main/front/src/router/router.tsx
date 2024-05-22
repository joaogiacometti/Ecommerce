import { createBrowserRouter } from "react-router-dom";
import NotFoundPage from '@/views/NotFoundPage.tsx'
import HomePage from '@/views/HomePage.tsx'
import ProductsPage from '@/views/ProductsPage.tsx'

const router = createBrowserRouter(
    [{
      path: '/',
      element: <HomePage/>,
      errorElement: <NotFoundPage/>
    },
    {
      path: '/products',
      element: <ProductsPage />
    }]
)

export default router