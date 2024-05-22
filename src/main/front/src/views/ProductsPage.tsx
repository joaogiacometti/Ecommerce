import { useState } from "react";
import "./products.css";
import { Card } from "@/components/card/card";
import { CreateModal } from "@/components/create-modal/create-modal";
import { useProduct } from "@/hooks/useProducts";
import { Link } from "react-router-dom";

function ProductsPage() {
  const { data } = useProduct();
  const [createModal, setCreateModal] = useState(false);

  const handleOpenCreateModal = () => {
    setCreateModal(!createModal);
  };

  return (
    <div className="container">
      <Link to="/">Home</Link>

      <h1>Products</h1>
      <div className="card-grid">
        {data?.map((product) => (
          <Card
            key={product.id}
            price={product.price}
            name={product.name}
            image={product.image}
          />
        ))}
      </div>
      {createModal && <CreateModal closeModal={handleOpenCreateModal} />}
      <button onClick={handleOpenCreateModal}>Cadastrar</button>
    </div>
  );
}

export default ProductsPage;
