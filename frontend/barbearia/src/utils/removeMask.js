export function removeMask(value) {
  return value.replace(/[^a-zA-Z0-9]/g, "");
}
