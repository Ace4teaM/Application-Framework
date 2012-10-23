#ifndef PARAM_H
#define PARAM_H

#include "../iface/iparam.h"

#include <iostream>
#include <string>
#include <map>

using namespace std;

namespace afw{
    class Param
    {
    protected:
        char* title;
        std::map<std::string, std::string> fields;
    public:
        Param(char* title);
        const char* getTitle();
        const char* get(const char* name);
        const char* set(const char* name,const char* value);
    };
}

#endif // PARAM_H
