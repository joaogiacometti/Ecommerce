import { Button, CardContent, CardMedia, Typography } from "@mui/material";
import Card from "@mui/material/Card";

interface CardProps {
  price: number;
  name: string;
  image: string;
}

export function CardComponent({ price, image, name }: CardProps) {
  return (
    <Card variant="outlined">
      <CardMedia component="img" height="140" image={image} alt={name} />
      <CardContent>
        <Typography variant="h5" component="div">
          {name}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          Price: ${price}
        </Typography>
        <Typography variant="body2" color="text.secondary" sx={{ mt: 2 }}>
          <Button variant="contained" color="primary">
            Buy
          </Button>
        </Typography>
      </CardContent>
    </Card>
  );
}
