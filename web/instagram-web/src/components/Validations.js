
export const email = (control) => {
  const mailFormat = /(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/;
  return !mailFormat.test(control.value) ? "Email is invalid" : "";
}

export const url = (control) => {
  const urlFormat = /([--:\w?@%&+~#=]*\.[a-z]{2,4}\/{0,2})((?:[?&](?:\w+)=(?:\w+))+|[--:\w?@%&+~#=]+)?/;
  return control.value && !urlFormat.test(control.value) ? "Url is invalid" : "";
}

export const required = (control) => {
  return control.value == "" ? "The field '" + control.label + "' is required. " : "";
}

export const maxLength = (control, max) => {
  return control.value.length > max ? "Max length: " + max : "";
}

export const minLength = (control, min) => {
  return control.value.length < min ? "Min length: " + min : "";
}

export const validateControl = (control) => {
  control.error = "";
  control.validations.forEach(validation => {
    control.error += validation(control);
  });

  return control;
  
}

export default validateControl;
