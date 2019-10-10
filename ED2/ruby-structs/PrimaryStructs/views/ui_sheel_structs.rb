# frozen_string_literal: true

def print_simple_list(target_cell)
  print "[ #{target_cell.value} -> "
  while target_cell = target_cell.successor
    print "#{target_cell.value} -> "
  end
  print "nil] \n\n"
end

def print_list_search_by_value(cell)
  if cell
    print "=> There is Element : [#{cell.value}] = \n"
  else
    print "=> Not Found Element : [#{cell.value}]\n"
  end
end

def print_dup_list(target_cell)
  print "[ #{target_cell.value} <-> "
  while target_cell = target_cell.successor
    print "#{target_cell.value} <-> "
  end
  print "nil] \n\n"
end