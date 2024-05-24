import { useUserMutation } from "@/hooks/useUsersMutate";
import { User } from "@/interfaces/User";
import { Box, Button, TextField } from "@mui/material";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import ErrorSnackbar from "./ErrorSnackbar";
import { setToken } from "@/helpers/AuthHelper";
import Token from "@/interfaces/Token";

export default function Login() {
  const { data, mutate, isSuccess, isError, error } = useUserMutation();
  const [errorSnackbar, setErrorSnackbar] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const formData = new FormData(e.currentTarget);

    const user: User = {
      login: formData.get("login") as string,
      password: formData.get("password") as string,
    };

    mutate(user);
  };

  useEffect(() => {
    if (isSuccess) {
      const tokenResponse: Token = data!.data;
      console.log(tokenResponse);
      setToken(tokenResponse);
      navigate("/");
    } else if (isError) {
      setErrorSnackbar(true);
    }
  }, [isSuccess, isError]);

  return (
    <>
      <Box
        component="form"
        onSubmit={(e) => handleSubmit(e)}
        noValidate
        sx={{ mt: 1 }}
      >
        <TextField
          margin="normal"
          required
          fullWidth
          id="login"
          label="Username"
          name="login"
          autoComplete="login"
          autoFocus
        ></TextField>
        <TextField
          margin="normal"
          required
          fullWidth
          id="password"
          label="Password"
          name="password"
          autoComplete="password"
        ></TextField>
        <Button
          type="submit"
          fullWidth
          variant="contained"
          sx={{ mt: 3, mb: 2 }}
        >
          Sign In
        </Button>
      </Box>
      {errorSnackbar && (
        <ErrorSnackbar
          message={`Failed to login: ${(error as any)?.response?.data}`}
        ></ErrorSnackbar>
      )}
    </>
  );
}
