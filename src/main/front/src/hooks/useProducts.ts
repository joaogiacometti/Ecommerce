import axios from 'axios';
import { Product } from '../interfaces/Product';
import { useQuery } from '@tanstack/react-query';

const fetchData = async (): Promise<Product[]> => {
    const response = await axios.get((import.meta as any).env.VITE_BASE_URL + "/products/getAll");
    return response.data;
};

export function useProduct() {
    const query = useQuery({
        queryFn: fetchData,
        queryKey: ['products'],
        retry: 2,
    });

    return {
        ...query,
        data: query.data 
    };
}
