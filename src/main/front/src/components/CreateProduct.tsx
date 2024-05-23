import { useEffect, useState } from "react";
import { useProductMutation } from "@/hooks/useProductsMutate";
import { Product } from "@/interfaces/Product";
import {
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  TextField,
  Button,
} from "@mui/material";
import ErrorSnackbar from "./ErrorSnackbar";
import SuccessSnackbar from "./SuccessSnackbar";

interface CloseModalProps {
  closeModal(): void;
}

interface InputProps {
  label: string;
  value: string | number;
  updateValue(value: any): void;
}

const Input = ({ label, value, updateValue }: InputProps) => {
  return (
    <TextField
      fullWidth
      margin="normal"
      label={label}
      value={value}
      onChange={(e) => updateValue(e.target.value)}
    />
  );
};

export function CreateProduct({ closeModal }: CloseModalProps) {
  const [name, setName] = useState("");
  const [price, setPrice] = useState(0);
  const [image, setImage] = useState("");
  const [errorSnackbar, setErrorSnackbar] = useState(false);
  const { mutate, isSuccess, isError, error, isPending } = useProductMutation();

  const submit = () => {
    const product: Product = {
      name,
      price,
      image,
    };

    mutate(product);
  };

  useEffect(() => {
    if (isSuccess) {
      closeModal();
    } else if (isError) {
      setErrorSnackbar(true);
    }
  }, [isSuccess, isError]);

  return (
    <>
      <Dialog open onClose={closeModal}>
        <DialogTitle>Create a new product</DialogTitle>
        <DialogContent>
          <form onSubmit={(e) => e.preventDefault()}>
            <Input label="Name" value={name} updateValue={setName} />
            <Input label="Price" value={price} updateValue={setPrice} />
            <Input label="Image" value={image} updateValue={setImage} />
          </form>
        </DialogContent>
        <DialogActions>
          <Button onClick={closeModal} color="secondary">
            Cancel
          </Button>
          <Button onClick={submit} color="primary" disabled={isPending}>
            {isPending ? "Creating..." : "Create"}
          </Button>
        </DialogActions>
      </Dialog>
      {errorSnackbar && (
        <ErrorSnackbar message={`Failed to create product: ${error}`} />
      )}
    </>
  );
}
