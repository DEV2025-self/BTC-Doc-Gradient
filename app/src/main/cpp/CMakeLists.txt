
cmake_minimum_required(VERSION 3.10.2)

project("com.doc.gradient.bt.server.uses.ai")

add_library(server_config SHARED server_config.cpp)

find_library(log-lib log)

target_link_libraries(server_config ${log-lib})