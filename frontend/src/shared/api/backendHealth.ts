export type BackendHealth = {
  status: string
}

export async function getBackendHealth(): Promise<BackendHealth> {
  const response = await fetch('/actuator/health')

  if (!response.ok) {
    throw new Error(
      `Backend health request failed with status ${response.status}`,
    )
  }

  return response.json() as Promise<BackendHealth>
}
