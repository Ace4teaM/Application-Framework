#ifndef IPLUGIN_H
#define IPLUGIN_H

#include "icommand.h"

#include <stdlib.h>
using namespace std;

namespace afw
{
    template <class T_Command,class T_String,class T_Icon> class IPlugin
    {
    public:
        virtual ICommandGroup<T_Command,T_String>* getCommandGroup() = 0;
        virtual T_String getDescription() = 0;
        virtual T_Icon getIcon() = 0;
    };
}

#endif // IPLUGIN_H
