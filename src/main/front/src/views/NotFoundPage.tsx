import { Link } from "react-router-dom"

export default function NotFoundPage() {
  return (
    <div className="flex flex-col gap-2">
        <div>404 Not Found</div>
        <Link to="/">Home</Link>
    </div>
  )
}
