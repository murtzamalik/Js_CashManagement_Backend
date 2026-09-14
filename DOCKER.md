# DFS Cash Management — Local Docker

## Services
| Service | URL |
|---------|-----|
| Frontend (UI) | http://localhost:3080 |
| Backend API | http://localhost:8020/jscash |
| T24 mock | http://localhost:18080 |

## Prerequisites
- Docker Desktop running
- Oracle DB reachable from Docker (schema already loaded)
- Copy env file: `cp .env.example .env` and set `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`

## Start
```bash
docker compose up --build -d
```

## Logs
```bash
docker compose logs -f backend
docker compose logs -f frontend
```

## Stop
```bash
docker compose down
```

## Notes
- Frontend nginx proxies `/jscash/` to the backend container.
- Bank/master-config APIs (`/jscashMasterConfig`) are not in this repo; those screens need that separate service.
- Backend uses profile `docker` (`application-docker.properties`).
