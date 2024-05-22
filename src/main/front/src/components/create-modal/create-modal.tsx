import { useEffect, useState } from "react";
import { useProductMutation } from "../../hooks/useProductsMutate";
import { Product } from "../../interfaces/Product";

import "./create-modal.css";

interface closeModalProps {
  closeModal(): void;
}

interface InputProps {
  label: string;
  value: string | number;
  updateValue(value: any): void; //TODO: solve the type of value
}

const Input = ({ label, value, updateValue }: InputProps) => {
  return (
    <>
      <label>{label}</label>
      <input value={value} onChange={(e) => updateValue(e.target.value)} />
    </>
  );
};

export function CreateModal({ closeModal }: closeModalProps) {
  const [name, setName] = useState("");
  const [price, setPrice] = useState(0);
  const [image, setImage] = useState("");
  const { mutate, isSuccess, isPending } = useProductMutation();

  const submit = () => {
    const product: Product = {
      name,
      price,
      image,
    };

    console.log(product);

    mutate(product);
  };

  useEffect(() => {
    if (isSuccess) {
      closeModal();
    }
  }, [isSuccess]);

  return (
    <div className="modal-overlay">
      <div className="modal-body">
        <h2>Cadastre um novo produto</h2>
        <form className="input-container" onSubmit={(e) => e.preventDefault()}>
          <Input label="name" value={name} updateValue={setName}></Input>
          <Input label="price" value={price} updateValue={setPrice}></Input>
          <Input label="image" value={image} updateValue={setImage}></Input>

          <button className="btn-secondary" onClick={submit}>
            {isPending ? "Cadastrando..." : "Cadastrar"}
          </button>
        </form>
      </div>
    </div>
  );
}
