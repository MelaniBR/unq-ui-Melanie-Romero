import { Swal } from "sweetalert2/dist/sweetalert2"

export const ErrorMessage = () => {

  Swal.fire({
    icon: 'error',
    title: 'Oops...',
    text: 'Something went wrong!'
  })
}