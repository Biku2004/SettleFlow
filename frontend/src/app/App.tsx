import { useEffect, useState } from 'react'
import {
  getBackendHealth,
  type BackendHealth,
} from '../shared/api/backendHealth'
import './App.css'

type ConnectionState =
  | { status: 'checking' }
  | { status: 'connected'; backend: BackendHealth }
  | { status: 'disconnected'; message: string }

function App() {
  const [connection, setConnection] = useState<ConnectionState>({
    status: 'checking',
  })

  useEffect(() => {
    async function checkBackend() {
      try {
        const health = await getBackendHealth()

        setConnection({
          status: 'connected',
          backend: health,
        })
      } catch (error) {
        setConnection({
          status: 'disconnected',
          message:
            error instanceof Error
              ? error.message
              : 'Unknown backend connection error',
        })
      }
    }

    void checkBackend()
  }, [])

  return (
    <main className="app">
      <section className="card">
        <p className="eyebrow">Payment &amp; Settlement Platform</p>

        <h1>SettleFlow</h1>

        <p className="description">
          Project foundation is running. Business features will be added one
          vertical slice at a time.
        </p>

        <div className="connection">
          <span>Backend</span>

          {connection.status === 'checking' && <strong>Checking...</strong>}

          {connection.status === 'connected' && (
            <strong>Connected — {connection.backend.status}</strong>
          )}

          {connection.status === 'disconnected' && <strong>Disconnected</strong>}
        </div>

        {connection.status === 'disconnected' && (
          <p className="error">{connection.message}</p>
        )}
      </section>
    </main>
  )
}

export default App
