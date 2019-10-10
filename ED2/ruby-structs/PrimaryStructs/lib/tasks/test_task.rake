# Rakefile
require "bundler/gem_tasks"
require "rake/testtask"

Rake::TestTask.new(bench: :build_lib) do |t|
  t.libs = %w[lib test]
  t.pattern = 'test/**/*_benchmark.rb'
end