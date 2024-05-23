import * as React from "react";
import Snackbar from "@mui/material/Snackbar";
import Alert from "@mui/material/Alert";

interface SuccessSnackbarProps {
  message: string;
}

export default function SuccessSnackbar(props: SuccessSnackbarProps) {
  const [open, setOpen] = React.useState(true);

  const handleClose = (
    event?: React.SyntheticEvent | Event,
    reason?: string
  ) => {
    if (reason === "clickaway") {
      return;
    }

    setOpen(false);
  };

  return (
    <div>
      <Snackbar open={open} autoHideDuration={3000} onClose={handleClose}>
        <Alert
          onClose={handleClose}
          severity="success"
          variant="filled"
          sx={{ width: "100%" }}
        >
          {props.message}
        </Alert>
      </Snackbar>
    </div>
  );
}
