import axios from 'axios';
import { Product } from '@/interfaces/Product';
import { useMutation, useQueryClient } from '@tanstack/react-query';

const postData = async (data: Product): Promise<any> => {
    const response = axios.post((import.meta as any).env.VITE_BASE_URL + "/products/create", data);
    
    return response;
};

export function useProductMutation() {
    const queryClient = useQueryClient();
    
    const mutate = useMutation({
        mutationFn: postData,
        retry: 2,
        onSuccess: () =>{
            queryClient.invalidateQueries({queryKey: ["products"]}); 
        }
    });

    return mutate;
}
