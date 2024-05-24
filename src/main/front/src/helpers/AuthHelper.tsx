import Token from "@/interfaces/Token";

export const setToken = (tokenResponse: Token) => {
  const roles = tokenResponse.roles.map((role) => role.name);

  localStorage.setItem("token", tokenResponse.token);
  localStorage.setItem("login", tokenResponse.login);
  localStorage.setItem("roles", JSON.stringify(roles));
};
