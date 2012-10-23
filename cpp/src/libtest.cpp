#include <QtCore/qglobal.h>

extern "C" Q_DECL_EXPORT int foo(int value) {
  return value + 42;
}
