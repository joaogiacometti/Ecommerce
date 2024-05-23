import axios, { AxiosResponse } from "axios";
import { User } from "@/interfaces/User";
import { useMutation } from "@tanstack/react-query";

const postData = async (data: User): Promise<AxiosResponse> => {
  const response = await axios.post(
    (import.meta as any).env.VITE_BASE_URL + "/auth/login",
    data
  );

  return response;
};

export function useUserMutation() {
  const mutate = useMutation({
    mutationFn: postData,
  });

  return {
    ...mutate,
    data: mutate.data,
  };
}
