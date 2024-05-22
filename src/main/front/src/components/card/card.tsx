import "./card.css"

interface CardProps{
    price: number,
    name: string,
    image: string,
}

export function Card({price, image, name}: CardProps){
    return(
        <div className="card">
            <img src={image} alt="" />
            <h2>{name}</h2>
            <p>Price: ${price}</p>
        </div>
    )
}