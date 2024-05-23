import { useState } from "react";
import { CardComponent } from "@/components/CardComponent";
import { CreateProduct } from "@/components/CreateProduct";
import { useProduct } from "@/hooks/useProducts";
import NavBar from "@/components/NavBar";
import { Box, Button, Container, Grid, Typography } from "@mui/material";

function ProductsPage() {
  const { data } = useProduct();
  const [modal, setModal] = useState(false);

  const handleOpenModal = () => {
    setModal(!modal);
  };

  return (
    <>
      <NavBar></NavBar>
      <Container>
        <Box
          display="flex"
          flexDirection="column"
          alignItems="center"
          mt={5}
          mb={3}
        >
          <Typography variant="h2" gutterBottom>
            Products
          </Typography>
        </Box>
        <Grid container spacing={3}>
          {data?.map((product) => (
            <Grid item key={product.id} xs={12} sm={6} md={4}>
              <CardComponent
                price={product.price}
                name={product.name}
                image={product.image}
              />
            </Grid>
          ))}
        </Grid>
        {modal && <CreateProduct closeModal={handleOpenModal} />}
        <Box display="flex" justifyContent="center" mt={4}>
          <Button variant="contained" color="primary" onClick={handleOpenModal}>
            Create
          </Button>
        </Box>
      </Container>
    </>
  );
}

export default ProductsPage;
