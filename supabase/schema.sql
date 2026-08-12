-- Ram Lekhak cloud sync schema.
-- Run this once in the Supabase SQL Editor (Project > SQL Editor > New query)
-- for a fresh project. Safe to re-run (uses IF NOT EXISTS / OR REPLACE).

create table if not exists public.practice_data (
  user_id uuid primary key references auth.users (id) on delete cascade,
  user_name text not null default 'Ram Bhakt',
  today_count integer not null default 0,
  total_count integer not null default 0,
  current_month_count integer not null default 0,
  current_month integer not null default extract(month from now()),
  current_streak integer not null default 0,
  longest_streak integer not null default 0,
  last_active_date text not null default '',
  daily_goal integer not null default 108,
  monthly_goal integer not null default 3240,
  theme text not null default 'ram',
  language text not null default 'en',
  updated_at timestamptz not null default now()
);

alter table public.practice_data enable row level security;

drop policy if exists "Users can view own practice data" on public.practice_data;
create policy "Users can view own practice data"
  on public.practice_data for select
  using (auth.uid() = user_id);

drop policy if exists "Users can insert own practice data" on public.practice_data;
create policy "Users can insert own practice data"
  on public.practice_data for insert
  with check (auth.uid() = user_id);

drop policy if exists "Users can update own practice data" on public.practice_data;
create policy "Users can update own practice data"
  on public.practice_data for update
  using (auth.uid() = user_id);

-- Keeps updated_at current on every write, used to resolve which copy
-- (local vs. cloud) is newer when a device reconnects.
create or replace function public.set_updated_at()
returns trigger as $$
begin
  new.updated_at = now();
  return new;
end;
$$ language plpgsql;

drop trigger if exists set_practice_data_updated_at on public.practice_data;
create trigger set_practice_data_updated_at
  before update on public.practice_data
  for each row
  execute function public.set_updated_at();
